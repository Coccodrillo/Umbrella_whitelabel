package org.secfirst.core.storage

import android.util.Log
import io.reactivex.Single
import org.secfirst.core.storage.TentConfig.Companion.DELIMITER_CATEGORY
import org.secfirst.core.storage.TentConfig.Companion.DELIMITER_SUBCATEGORY
import java.io.File


class CategoryAdapter(private val tentConfig: TentConfig) : TentConfig.Serializable {

    private val lesson: Lesson = Lesson()
    private val fileList: MutableList<File> = arrayListOf()

    override fun serialize(): Single<Lesson> {
        File(tentConfig.getPathRepository())
                .walk()
                .filter { !it.path.contains(".git") }
                .filter { it.isFile }
                .forEach { file -> fileList.add(file) }

        fileList.reverse()
        processCategory()
        return Single.just(lesson)
    }

    private fun processFile(currentFile: File) {
        when (getFileOfName(currentFile)) {
            TypeFile.CATEGORY -> Log.d("", "")
            TypeFile.CHECKLIST -> processCheckList(currentFile)
            TypeFile.FORM -> processform(currentFile)
            else -> {
            }
        }
    }

    private fun processCheckList(currentFile: File) {

    }

    private fun processform(currentFile: File) {

    }

    private fun processCategory() {
        fileList.filter { file -> file.name == ".category.yml" }
                .forEach { currentFile ->
                    val pwd = currentFile.path
                            .removeSuffix(".category.yml")
                            .substringAfterLast("en/", "")

                    Log.i("test", "path - ${currentFile.absoluteFile}")
                    val category = parseYmlFile(currentFile, Category::class)
                    val cleanDirectories = checkIfHasAnotherCategory(pwd.split("/").filter { it.isNotEmpty() })
                    cleanDirectories.forEachIndexed { index, _ ->
                        if (index == 0) {
                            category.path = pwd
                            category.rootDir = cleanDirectories[index]
                        } else {
                            val subcategory = parseYmlFile(currentFile, Category::class)
                            subcategory.path = pwd
                            subcategory.rootDir = cleanDirectories[index]
                            category.subcategories.add(subcategory)
                        }
                    }
                    addCategory(category)
                }
    }

    private fun addCategory(category: Category) {
        val directories = category.path.split("/").filter { it.isNotEmpty() }
        val lastCategory = lesson.categories.lastOrNull()
        val lastSubcategory = lastCategory?.subcategories?.lastOrNull()

        if (directories.size == DELIMITER_CATEGORY)
            lesson.categories.add(category)
        else if (directories.size == DELIMITER_SUBCATEGORY) {
            if (lastCategory != null && directories[0] == lastCategory.rootDir)
                lastCategory.subcategories.add(category)
        } else lastSubcategory?.subcategories?.add(category)
    }


    private fun getExtensionFile(file: File): TypeFile {
        val extension = file.path.substringAfterLast('.', "")
        return when (extension) {
            TypeFile.CATEGORY.value -> TypeFile.CATEGORY
            TypeFile.CHECKLIST.value -> TypeFile.CHECKLIST
            TypeFile.FORM.value -> TypeFile.FORM
            else -> TypeFile.NOUN
        }
    }


    /**
     * Walk through last category and last subcategory of last category
     * checking @param directories is already created.
     *
     * @return list of directories that need to be created.
     */
    private fun checkIfHasAnotherCategory(directories: List<String>): List<String> {

        val auxList = arrayListOf<String>()
        val lastCategory = lesson.categories.lastOrNull()
        val lastSubcategory = lastCategory?.subcategories?.lastOrNull()
        val lastCategoryIndex = 0

        directories.forEachIndexed { index, directoryName ->

            if (index == lastCategoryIndex) {
                if (lastCategory != null && directoryName != lastCategory.rootDir) {
                    auxList.add(directoryName)
                }
            } else if (lastSubcategory == null || directoryName != lastSubcategory.rootDir) {
                auxList.add(directoryName)
            }
        }
        return if (auxList.isEmpty()) directories else auxList
    }

    private fun getFileOfName(file: File): TypeFile {
        return when (file.name) {
            TypeFile.CATEGORY.value -> TypeFile.CATEGORY
            TypeFile.CHECKLIST.value -> TypeFile.CHECKLIST
            TypeFile.FORM.value -> TypeFile.FORM
            else -> TypeFile.NOUN
        }
    }
}