package org.secfirst.core.logic

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.secfirst.core.storage.Root
import org.secfirst.core.storage.TypeFile
import java.io.File
import kotlin.reflect.KClass

interface Serialize {

    fun serialize(typeFile: TypeFile, pRoot: Root): Root

    fun <T : Any> parseYmlFile(file: File, c: KClass<T>): T {
        val mapper = ObjectMapper(YAMLFactory())
        mapper.registerModule(KotlinModule())
        return file.bufferedReader().use { mapper.readValue(it.readText(), c.java) }
    }

    fun getLastDirectory(path: String): String {
        val splitPath = path.split("/").filter { it.isNotEmpty() }
        return splitPath[splitPath.lastIndex]
    }

    fun getSplitPath(path: String) = path.split("/").filter { it.isNotEmpty() }

    fun getLevelOfPath(path: String) = getSplitPath(path).size

    fun getWorkDirectory(path: String): String {
        val splitPath = getSplitPath(path)
        var pwd = ""
        for (i in 0 until splitPath.size - 1)
            pwd += splitPath[i] + "/"
        return pwd
    }

    fun getDelimiter(fileName: String): String {
        return if (fileName == TypeFile.CATEGORY.value)
            fileName
        else
            fileName.substringBeforeLast("_")
    }

}