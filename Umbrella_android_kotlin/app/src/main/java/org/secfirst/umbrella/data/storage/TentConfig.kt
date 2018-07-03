package org.secfirst.umbrella.data.storage

import java.io.File

class TentConfig(private val repoPath: String) {

    companion object {
        const val BRANCH_NAME: String = "refs/heads/master"
        const val URI_REPOSITORY = "https://github.com/douglasalipio/umbrella-content.git"
        const val FORM_NAME = "forms"
        const val HIERARCHY_ELEMENT = 1
        const val HIERARCHY_SUB_ELEMENT = 2
        const val HIERARCHY_CHILD = 3
        fun getDelimiter(fileName: String): String {
            return if (fileName == TypeFile.CATEGORY.value)
                fileName
            else
                fileName.substringBeforeLast("_")
        }
    }

    fun isRepositoryPath() = File(repoPath).exists()
    fun isNotRepositoryPath() = !File(repoPath).exists()
    fun getPathRepository(): String = repoPath

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