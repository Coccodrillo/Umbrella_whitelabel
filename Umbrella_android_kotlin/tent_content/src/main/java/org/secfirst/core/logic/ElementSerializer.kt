package org.secfirst.core.logic

import android.util.Log
import org.secfirst.core.storage.Element
import org.secfirst.core.storage.Root
import org.secfirst.core.utils.PathUtils.Companion.getLastDirectory
import org.secfirst.core.utils.PathUtils.Companion.getLevelOfPath
import org.secfirst.core.utils.PathUtils.Companion.getWorkDirectory
import org.secfirst.core.utils.TentConfig.Companion.DELIMITER_ELEMENT
import org.secfirst.core.utils.TentConfig.Companion.DELIMITER_SUB_ELEMENT
import java.io.File


class ElementSerializer : Serialize {

    private val root: Root = Root()
    private var fileList = listOf<File>()


    fun serialize(pFiles: List<File>): Root {
        fileList = pFiles
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