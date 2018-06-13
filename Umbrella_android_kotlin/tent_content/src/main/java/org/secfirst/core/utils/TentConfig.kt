package org.secfirst.core.utils

import android.content.Context
import java.io.File

class TentConfig(val context: Context) {

    companion object {
        const val BRANCH_NAME: String = "refs/heads/master"
        const val URI_REPOSITORY = "https://github.com/klaidliadon/umbrella-content.git"
        const val FORM_NAME = "forms"
        const val DELIMITER_ELEMENT = 1
        const val DELIMITER_SUB_ELEMENT = 2
        const val DELIMITER_SUB_SUB_ELEMENT = 3
        fun getDelimiter(fileName: String): String {
            return if (fileName == TypeFile.CATEGORY.value)
                fileName
            else
                fileName.substringBeforeLast("_")
        }
    }

    fun isRepositoryPath() = File(context.cacheDir.path + "/repo/").exists()
    fun isNotRepositoryPath() = !File(context.cacheDir.path + "/repo/").exists()
    fun getPathRepository(): String = context.cacheDir.path + "/repo/"

}

enum class TypeFile(val value: String) {
    CHECKLIST("c"),
    FORM("f"),
    CATEGORY(".category"),
    SEGMENT("s"),
    NOUN("")
}

enum class ExtensionFile(val value: String) {
    YML("yml"),
    MD("md")
}