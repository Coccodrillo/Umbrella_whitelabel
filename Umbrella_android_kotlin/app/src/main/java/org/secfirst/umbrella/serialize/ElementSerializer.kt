package org.secfirst.umbrella.serialize

import android.util.Log
import org.secfirst.umbrella.data.Element
import org.secfirst.umbrella.data.Root
import org.secfirst.umbrella.data.storage.TentConfig.Companion.HIERARCHY_ELEMENT
import org.secfirst.umbrella.data.storage.TentConfig.Companion.HIERARCHY_SUB_ELEMENT
import org.secfirst.umbrella.data.storage.TentStorageRepo
import org.secfirst.umbrella.serialize.PathUtils.Companion.getLastDirectory
import org.secfirst.umbrella.serialize.PathUtils.Companion.getLevelOfPath
import org.secfirst.umbrella.serialize.PathUtils.Companion.getWorkDirectory
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
        element.rootDir = getLastDirectory(pwd)
        when (getLevelOfPath(element.path)) {
            HIERARCHY_ELEMENT -> root.categories.add(element)
            HIERARCHY_SUB_ELEMENT -> root.categories.last().children.add(element)
            else -> root.categories.last().children.last().children.add(element)
        }
    }
}