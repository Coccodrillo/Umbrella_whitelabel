package org.secfirst.core.logic

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
                .filter { file -> getDelimiter(file.name) == TypeFile.SEGMENT.value }
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

            addSegments(pwd, currentFile)
        }
    }

    private fun addSegments(pwd: String, file: File) {
        when (getLevelOfPath(pwd)) {
            TentConfig.DELIMITER_ELEMENT -> {
                root.elements.forEach {
                    if (it.path == pwd) {
                        it.markdowns.add(file)
                    }
                }
            }

            TentConfig.DELIMITER_SUB_ELEMENT -> {
                root.elements.forEach { element ->
                    element.children.forEach { child ->
                        if (child.path == pwd) {
                            child.markdowns.add(file)
                        }
                    }
                }
            }
            TentConfig.DELIMITER_SUB_SUB_ELEMENT -> {
                root.elements.forEach { element ->
                    element.children.forEach { child ->
                        child.children.forEach { grandchild ->
                            if (grandchild.path == pwd) {
                                grandchild.markdowns.add(file)
                            }
                        }
                    }
                }
            }
        }
    }
}