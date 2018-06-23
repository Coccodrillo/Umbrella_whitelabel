package org.secfirst.content.serialize

import org.secfirst.content.PathUtils.Companion.getLastDirectory
import org.secfirst.content.PathUtils.Companion.getLevelOfPath
import org.secfirst.content.PathUtils.Companion.getWorkDirectory
import org.secfirst.content.storage.*
import org.secfirst.content.storage.TentConfig.Companion.FORM_NAME
import org.secfirst.content.storage.TentConfig.Companion.getDelimiter
import java.io.File

class ElementLoader : Serialize {

    private var root: Root = Root()
    private var files = listOf<File>()

    fun load(pRoot: Root, pFiles: List<File>): Root {
        root = pRoot
        files = pFiles
        create()
        return root
    }

    private fun create() {
        files.forEach { currentFile ->
            val absolutePath = currentFile.path.substringAfterLast("en/", "")
            val pwd = getWorkDirectory(absolutePath)
            if (getLastDirectory(pwd) == FORM_NAME) addForms(currentFile) else addProperties(pwd, currentFile)
        }
    }

    private fun addProperties(pwd: String, file: File) {
        when (getLevelOfPath(pwd)) {
            TentConfig.HIERARCHY_ELEMENT -> {
                root.elements.forEach {
                    if (it.path == pwd) {
                        when (getDelimiter(file.nameWithoutExtension)) {
                            TypeFile.SEGMENT.value -> it.markdowns.add(Markdown(file.readText()))
                            TypeFile.CHECKLIST.value -> it.checklist.add(parseYmlFile(file, CheckList::class))
                        }
                    }
                }
            }

            TentConfig.HIERARCHY_SUB_ELEMENT -> {
                root.elements.forEach { element ->
                    element.children.forEach { child ->
                        if (child.path == pwd) {
                            when (getDelimiter(file.nameWithoutExtension)) {
                                TypeFile.SEGMENT.value -> child.markdowns.add(Markdown(file.readText()))
                                TypeFile.CHECKLIST.value -> child.checklist.add(parseYmlFile(file, CheckList::class))
                            }
                        }
                    }
                }
            }
            TentConfig.HIERARCHY_SUB_SUB_ELEMENT -> {
                root.elements.forEach { element ->
                    element.children.forEach { child ->
                        child.children.forEach { grandchild ->
                            if (grandchild.path == pwd) {
                                when (getDelimiter(file.nameWithoutExtension)) {
                                    TypeFile.SEGMENT.value -> grandchild.markdowns.add(Markdown(file.readText()))
                                    TypeFile.CHECKLIST.value -> grandchild.checklist.add(parseYmlFile(file, CheckList::class))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun addForms(file: File) {
        root.forms.add(parseYmlFile(file, Form::class))
    }
}