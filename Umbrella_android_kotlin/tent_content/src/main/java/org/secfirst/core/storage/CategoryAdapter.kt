package org.secfirst.core.storage

import android.util.Log
import java.io.File


class CategoryAdapter(private val tentConfig: TentConfig) : TentConfig.Serializable {

    private val lesson: Lesson = Lesson()


    private val fileList: MutableList<File> = arrayListOf()

    override fun serialize(): Lesson {
        File(tentConfig.getPathRepository())
                .walk()
                .filter { !it.path.contains(".git") }
                .filter { it.isFile }
                .forEach { file -> fileList.add(file) }

        fileList.reverse()
        processCategory()

        //processCategory("travel/kidnapping/beginner")
        return lesson
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

    private fun processCategory(path: String): Category? {
        val listOfDirectory = path.split("/".toRegex())

        //The root category (in your example, communications)
        var rootCategory: Category? = null
        //A reminder of the current Category, so we can attach the next one to it
        var currentCategory: Category? = null
        listOfDirectory.forEach {
            if (rootCategory == null) {
                //First element, so I need to create the root category
                rootCategory = Category(it)
                currentCategory = rootCategory
            } else {
                //Other elements are simply created
                val nextCategory = Category(it)
                //Added as a subCategory of the previous category
                currentCategory!!.subcategories.add(nextCategory)
                //And we progress within the chain
                currentCategory = nextCategory
            }
        }
        //In the end, my root category will contain :
        // Category("communications", Category("email", Category("Beginner", null)))
        return rootCategory
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
                    var category: Category? = null
                    val lastCategory: Category? = lesson.categories.lastOrNull()
                    val lastSubcategory = lastCategory?.subcategories?.lastOrNull()
                    val cleanDirectories = checkIfHasAnotherCategory(pwd.split("/").filter { it.isNotEmpty() })
                    // directories = pwd.split("/").filter { it.isNotEmpty() }
                    //if (lastCategory != null && lastCategory.rootDir == directories[0]) directories.drop(0)

                    cleanDirectories.forEachIndexed { index, s ->
                        if (index == 0) {
                            category = parseYmlFile(currentFile, Category::class)
                            category!!.path = pwd
                            category!!.rootDir = cleanDirectories[index]
                        } else {
                            val subCategory = parseYmlFile(currentFile, Category::class)
                            subCategory.path = pwd
                            subCategory.rootDir = cleanDirectories[index]
                            category!!.subcategories.add(subCategory)
                        }
                    }
                    addCategory(category!!)
                }
    }


    private fun addCategory(category: Category) {
        val directories = category.path.split("/").filter { it.isNotEmpty() }
        val lastCategory = lesson.categories.lastOrNull()
        val lastSubcategory = lastCategory?.subcategories?.lastOrNull()


        if (directories.size == 1) {
            lesson.categories.add(category)

        } else if (directories.size == 2) {
            if (lastCategory != null && directories[0] == lastCategory.rootDir) {
                lastCategory.subcategories.add(category)
            }

        } else {
            lastSubcategory?.subcategories?.add(category)
        }
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


    private fun checkIfHasAnotherCategory(directories: List<String>): List<String> {
        val auxList = arrayListOf<String>()
        val lastCategory = lesson.categories.lastOrNull()
        val lastSubcategory = lastCategory?.subcategories?.lastOrNull()
        directories.forEachIndexed { index, direcoryName ->

            if (index == 0) {
                if (lastCategory != null && direcoryName != lastCategory.rootDir) {
                    auxList.add(direcoryName)
                }
            } else if (lastSubcategory == null || direcoryName != lastSubcategory.rootDir) {
                auxList.add(direcoryName)
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