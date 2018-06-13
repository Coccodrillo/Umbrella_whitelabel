package org.secfirst.core.logic

import android.util.Log
import org.secfirst.core.PathUtils.Companion.getLastDirectory
import org.secfirst.core.PathUtils.Companion.getLevelOfPath
import org.secfirst.core.PathUtils.Companion.getWorkDirectory
import org.secfirst.core.storage.Element
import org.secfirst.core.storage.Root
import org.secfirst.core.storage.TentConfig
import org.secfirst.core.storage.TentConfig.Companion.DELIMITER_ELEMENT
import org.secfirst.core.storage.TentConfig.Companion.DELIMITER_SUB_ELEMENT
import java.io.File


class ElementSerializer(private val tentConfig: TentConfig) : Serialize {

    private val root: Root = Root()
    private val fileList: MutableList<File> = arrayListOf()

    fun serialize(): Root {
        File(tentConfig.getPathRepository())
                .walk()
                .filter { !it.path.contains(".git") }
                .filter { it.isFile }
                .filter { file -> file.name == ".category.yml" }
                .forEach { file -> fileList.add(file) }

        fileList.reverse()
        create()
        return root
    }

    private fun create() {
        fileList.forEach { currentFile ->
            val absolutePath = currentFile.path
                    .substringAfterLast("en/", "")
            val pwd = getWorkDirectory(absolutePath)
            Log.e("test", "path - $absolutePath")
            addElement(pwd, currentFile)
        }
    }

    private fun addElement(pwd: String, currentFile: File) {

        val element = parseYmlFile(currentFile, Element::class)
        element.path = pwd
        element.rootDir = getLastDirectory(pwd)
        when (getLevelOfPath(element.path)) {
            DELIMITER_ELEMENT -> root.elements.add(element)
            DELIMITER_SUB_ELEMENT -> root.elements.last().children.add(element)
            else -> root.elements.last().children.last().children.add(element)
        }
    }

}