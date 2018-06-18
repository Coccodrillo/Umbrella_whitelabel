package org.secfirst.core.logic

import org.secfirst.core.storage.CheckList
import org.secfirst.core.storage.Form
import org.secfirst.core.storage.Root
import org.secfirst.core.utils.PathUtils.Companion.getLastDirectory
import org.secfirst.core.utils.PathUtils.Companion.getLevelOfPath
import org.secfirst.core.utils.PathUtils.Companion.getWorkDirectory
import org.secfirst.core.utils.TentConfig
import org.secfirst.core.utils.TentConfig.Companion.FORM_NAME
import org.secfirst.core.utils.TentConfig.Companion.getDelimiter
import org.secfirst.core.utils.TypeFile
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