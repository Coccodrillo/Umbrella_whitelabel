package org.secfirst.core.storage

import android.content.Context
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.reactivex.Single
import java.io.File
import kotlin.reflect.KClass

class TentConfig(val context: Context) {

    companion object {
        const val BRANCH_NAME: String = "refs/heads/master"
        const val URI_REPOSITORY = "https://github.com/klaidliadon/umbrella-content.git"
        const val FORM_NAME = "forms"
        const val DELIMITER_CATEGORY = 1
        const val DELIMITER_SUBCATEGORY = 2
    }

    fun isRepositoryPath() = File(context.cacheDir.path + "/repo/").exists()
    fun isNotRepositoryPath() = !File(context.cacheDir.path + "/repo/").exists()
    fun getPathRepository(): String = context.cacheDir.path + "/repo/"

    interface Serializable {
        fun serialize(): Single<Lesson>
        fun <T : Any> parseYmlFile(file: File, c: KClass<T>): T {
            val mapper = ObjectMapper(YAMLFactory()) // Enable YAML parsing
            mapper.registerModule(KotlinModule()) // Enable Kotlin support
            return file.bufferedReader().use { mapper.readValue(it.readText(), c.java) }
        }
    }
}


enum class TypeFile(val value: String) {
    CHECKLIST("c.yml"),
    FORM("f.yml"),
    CATEGORY(".category.yml"),
    NOUN("")
}

enum class ExtensionFile(val value: String) {
    YML("yml"),
    MD("md")
}