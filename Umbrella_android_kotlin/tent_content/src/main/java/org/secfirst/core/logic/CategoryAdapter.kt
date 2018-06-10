package org.secfirst.core.logic

import io.reactivex.Single
import org.secfirst.core.storage.Element
import org.secfirst.core.storage.Root
import org.secfirst.core.storage.TentConfig
import org.secfirst.core.storage.TentConfig.Companion.DELIMITER_CATEGORY
import org.secfirst.core.storage.TentConfig.Companion.DELIMITER_SUBCATEGORY
import java.io.File


class CategoryAdapter(private val tentConfig: TentConfig) : TentConfig.Serializable {

    private val root: Root = Root()
    private val fileList: MutableList<File> = arrayListOf()

    override fun serialize(): Single<Root> {
        File(tentConfig.getPathRepository())
                .walk()
                .filter { !it.path.contains(".git") }
                .filter { it.isFile }
                .forEach { file -> fileList.add(file) }

        fileList.reverse()
        processCategory()
        return Single.just(root)
    }


    private fun processCategory() {
        fileList.filter { file -> file.name == ".category.yml" }
                .forEach { currentFile ->
                    val pwd = currentFile.path
                            .removeSuffix(".category.yml")
                            .substringAfterLast("en/", "")

                    val splitPath = pwd.split("/").filter { it.isNotEmpty() }
                    val directoryName = findElements(splitPath, root.elements.lastOrNull())
                    val element = parseYmlFile(currentFile, Element::class)
                    element.path = pwd
                    element.rootDir = directoryName
                    addElement(element)
                }
    }

    /**
     * Add objects as a element or children of element in a list of element
     */
    private fun addElement(element: Element) {
        val directories = element.path.split("/").filter { it.isNotEmpty() }
        when (directories.size) {
            DELIMITER_CATEGORY -> root.elements.add(element)
            DELIMITER_SUBCATEGORY -> root.elements.last().children.add(element)
            else -> root.elements.last().children.last().children.add(element)
        }
    }
}