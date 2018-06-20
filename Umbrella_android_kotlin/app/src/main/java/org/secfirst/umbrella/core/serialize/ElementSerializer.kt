package org.secfirst.umbrella.core.serialize

import android.util.Log
import org.secfirst.umbrella.data.storage.Element
import org.secfirst.umbrella.core.PathUtils.Companion.getLastDirectory
import org.secfirst.umbrella.core.PathUtils.Companion.getLevelOfPath
import org.secfirst.umbrella.core.PathUtils.Companion.getWorkDirectory
import org.secfirst.umbrella.data.storage.Root
import org.secfirst.umbrella.data.storage.TentConfig.Companion.HIERARCHY_ELEMENT
import org.secfirst.umbrella.data.storage.TentConfig.Companion.HIERARCHY_SUB_ELEMENT
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
            HIERARCHY_ELEMENT -> root.elements.add(element)
            HIERARCHY_SUB_ELEMENT -> root.elements.last().children.add(element)
            else -> root.elements.last().children.last().children.add(element)
        }
    }

}