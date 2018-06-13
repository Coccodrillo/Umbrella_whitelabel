package org.secfirst.core.logic

import org.secfirst.core.PathUtils.Companion.getLevelOfPath
import org.secfirst.core.PathUtils.Companion.getWorkDirectory
import org.secfirst.core.storage.CheckList
import org.secfirst.core.storage.Root
import org.secfirst.core.storage.TentConfig
import org.secfirst.core.storage.TentConfig.Companion.getDelimiter
import org.secfirst.core.storage.TypeFile
import java.io.File

class ElementLoader(private val tentConfig: TentConfig) : Serialize {

    private var root: Root = Root()
    private val files: MutableList<File> = arrayListOf()

    fun load(pRoot: Root): Root {
        root = pRoot
        File(tentConfig.getPathRepository())
                .walk()
                .filter { file -> !file.path.contains(".git") }
                .filter { file ->
                    getDelimiter(file.name) == TypeFile.SEGMENT.value || getDelimiter(file.name) == TypeFile.CHECKLIST.value
                }
                .filter { it.isFile }
                .forEach { file -> files.add(file) }

        files.reverse()
        create()
        return root
    }

    private fun create() {
        files.forEach { currentFile ->
            val absolutePath = currentFile.path
                    .substringAfterLast("en/", "")
            val pwd = getWorkDirectory(absolutePath)
            addProperties(pwd, currentFile)
        }
    }

    private fun addProperties(pwd: String, file: File) {
        when (getLevelOfPath(pwd)) {
            TentConfig.DELIMITER_ELEMENT -> {
                root.elements.forEach {
                    if (it.path == pwd) {
                        when (getDelimiter(file.nameWithoutExtension)) {
                            TypeFile.SEGMENT.value -> it.markdowns.add(file)
                            TypeFile.CHECKLIST.value -> it.checklist.add(parseYmlFile(file, CheckList::class))
                        }
                    }
                }
            }

            TentConfig.DELIMITER_SUB_ELEMENT -> {
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
            TentConfig.DELIMITER_SUB_SUB_ELEMENT -> {
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
}