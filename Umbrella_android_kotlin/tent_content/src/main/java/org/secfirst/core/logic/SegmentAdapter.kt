package org.secfirst.core.logic

import org.secfirst.core.storage.CheckList
import org.secfirst.core.storage.Root
import org.secfirst.core.storage.TentConfig
import org.secfirst.core.storage.TypeFile
import java.io.File

class SegmentAdapter(private val tentConfig: TentConfig) : Serialize {

    private var root: Root = Root()
    private val segments: MutableList<File> = arrayListOf()

    override fun serialize(typeFile: TypeFile, pRoot: Root): Root {
        root = pRoot
        File(tentConfig.getPathRepository())
                .walk()
                .filter { !it.path.contains(".git") }
                .filter { file ->
                    getDelimiter(file.name) == TypeFile.SEGMENT.value ||
                            getDelimiter(file.name) == TypeFile.CHECKLIST.value
                }
                .filter { it.isFile }
                .forEach { file -> segments.add(file) }

        segments.reverse()
        create()
        return root
    }

    fun create() {
        segments.forEach { currentFile ->
            val absolutePath = currentFile.path
                    .substringAfterLast("en/", "")
            val pwd = getWorkDirectory(absolutePath)

            findProperties(pwd, currentFile)
        }
    }

    private fun findProperties(pwd: String, file: File) {
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