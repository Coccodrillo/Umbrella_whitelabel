package org.secfirst.core.logic

import android.util.Log
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.reactivex.Single
import org.secfirst.core.storage.Element
import org.secfirst.core.storage.Root
import org.secfirst.core.storage.TentConfig
import org.secfirst.core.storage.TentConfig.Companion.DELIMITER_ELEMENT
import org.secfirst.core.storage.TentConfig.Companion.DELIMITER_SUB_ELEMENT
import java.io.File
import kotlin.reflect.KClass


class ElementAdapterImp (private val tentConfig: TentConfig) : TentContent {

    private val root: Root = Root()
    private val fileList: MutableList<File> = arrayListOf()

    override fun serialize(): Single<Root> {
        File(tentConfig.getPathRepository())
                .walk()
                .filter { !it.path.contains(".git") }
                .filter { it.isFile }
                .forEach { file -> fileList.add(file) }

        fileList.reverse()
        create()
        return Single.just(root)
    }

    private fun create() {
        fileList.filter { file -> file.name == ".category.yml" }
                .forEach { currentFile ->
                    val pwd = currentFile.path
                            .removeSuffix(".category.yml")
                            .substringAfterLast("en/", "")

                    Log.e("test", "path - $pwd")
                    val element = parseYmlFile(currentFile, Element::class)
                    element.path = pwd
                    element.rootDir = getLastDirectory(pwd)
                    addElement(element)
                }
    }

    /**
     * Add objects as a element or children of element in a list of element
     */
    private fun addElement(element: Element) {
        when (getLevelOfPath(element.path)) {
            DELIMITER_ELEMENT -> root.elements.add(element)
            DELIMITER_SUB_ELEMENT -> root.elements.last().children.add(element)
            else -> root.elements.last().children.last().children.add(element)
        }
    }

    private fun getLastDirectory(path: String): String {
        val splitPath = path.split("/").filter { it.isNotEmpty() }
        return splitPath[splitPath.lastIndex]
    }

    private fun getSplitPath(path: String) = path.split("/").filter { it.isNotEmpty() }

    private fun getLevelOfPath(path: String) = getSplitPath(path).size

    private fun <T : Any> parseYmlFile(file: File, c: KClass<T>): T {
        val mapper = ObjectMapper(YAMLFactory())
        mapper.registerModule(KotlinModule())
        return file.bufferedReader().use { mapper.readValue(it.readText(), c.java) }
    }
}