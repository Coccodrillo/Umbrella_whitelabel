package org.secfirst.whitelabel.serialize

import android.util.Log
import org.secfirst.whitelabel.data.Element
import org.secfirst.whitelabel.data.Root
import org.secfirst.whitelabel.data.storage.TentConfig
import org.secfirst.whitelabel.data.storage.TentStorageRepo
import org.secfirst.whitelabel.serialize.PathUtils.Companion.getWorkDirectory
import java.io.File
import javax.inject.Inject


class ElementSerializer @Inject constructor(private val tentStorageRepo: TentStorageRepo) : Serializer {

    private val root: Root = Root()
    private var fileList = listOf<File>()

    fun serialize(): Root {
        fileList = tentStorageRepo.getElementsFile()
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
        element.rootDir = PathUtils.getLastDirectory(pwd)
        when (PathUtils.getLevelOfPath(element.path)) {
            TentConfig.ELEMENT_LEVEL -> root.elements.add(element)
            TentConfig.SUB_ELEMENT_LEVEL -> root.elements.last().children.add(element)
            else -> root.elements.last().children.last().children.add(element)
        }
    }
}