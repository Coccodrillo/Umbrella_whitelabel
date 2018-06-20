package org.secfirst.umbrella.core.serialize

import org.secfirst.umbrella.data.storage.*
import org.secfirst.umbrella.data.storage.TentConfig.Companion.FORM_NAME
import org.secfirst.umbrella.data.storage.TentConfig.Companion.getDelimiter
import org.secfirst.umbrella.core.PathUtils.Companion.getLastDirectory
import org.secfirst.umbrella.core.PathUtils.Companion.getLevelOfPath
import org.secfirst.umbrella.core.PathUtils.Companion.getWorkDirectory

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
                            TypeFile.SEGMENT.value -> it.markdowns.add(file)
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
                                TypeFile.SEGMENT.value -> child.markdowns.add(file)
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
                                    TypeFile.SEGMENT.value -> grandchild.markdowns.add(file)
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