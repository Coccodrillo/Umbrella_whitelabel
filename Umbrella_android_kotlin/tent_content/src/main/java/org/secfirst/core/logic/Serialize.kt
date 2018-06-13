package org.secfirst.core.logic

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import java.io.File
import kotlin.reflect.KClass

interface Serialize {
    fun <T : Any> parseYmlFile(file: File, c: KClass<T>): T {
        val mapper = ObjectMapper(YAMLFactory())
        mapper.registerModule(KotlinModule())
        return file.bufferedReader().use { mapper.readValue(it.readText(), c.java) }
    }
}