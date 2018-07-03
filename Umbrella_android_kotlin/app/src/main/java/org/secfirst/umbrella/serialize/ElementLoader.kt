package org.secfirst.umbrella.serialize

import org.secfirst.umbrella.data.Checklist
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Markdown
import org.secfirst.umbrella.data.Root
import org.secfirst.umbrella.data.storage.TentConfig.Companion.FORM_NAME
import org.secfirst.umbrella.data.storage.TentConfig.Companion.HIERARCHY_CHILD
import org.secfirst.umbrella.data.storage.TentConfig.Companion.HIERARCHY_ELEMENT
import org.secfirst.umbrella.data.storage.TentConfig.Companion.HIERARCHY_SUB_ELEMENT
import org.secfirst.umbrella.data.storage.TentConfig.Companion.getDelimiter
import org.secfirst.umbrella.data.storage.TentStorageRepo
import org.secfirst.umbrella.data.storage.TypeFile
import org.secfirst.umbrella.serialize.PathUtils.Companion.getLastDirectory
import org.secfirst.umbrella.serialize.PathUtils.Companion.getLevelOfPath
import org.secfirst.umbrella.serialize.PathUtils.Companion.getWorkDirectory
import java.io.File
import javax.inject.Inject

class ElementLoader @Inject constructor(private val tentStorageRepo: TentStorageRepo) : Serializer {

    private var root = Root()
    private var files = listOf<File>()
    fun load(pRoot: Root): Root {
        files = tentStorageRepo.getLoadersFile()
        root = pRoot
        create()
        ignoreFormAsCategory()
        return root
    }

    private fun create() {
        files.forEach { currentFile ->
            val absolutePath = currentFile.path.substringAfterLast("en/", "")
            val pwd = getWorkDirectory(absolutePath)

            if (getLastDirectory(pwd) == FORM_NAME)
                addForms(currentFile)
            else addProperties(pwd, currentFile)
        }
    }

    private fun ignoreFormAsCategory() {
        val categoriesIterator = root.categories.iterator()
        for (form in categoriesIterator) {
            if (form.rootDir == FORM_NAME) {
                categoriesIterator.remove()
                continue
            }
        }
    }

    private fun addProperties(pwd: String, file: File) {
        when (getLevelOfPath(pwd)) {
            HIERARCHY_ELEMENT -> {
                root.categories.forEach {
                    if (it.path == pwd) {
                        when (getDelimiter(file.nameWithoutExtension)) {
                            TypeFile.SEGMENT.value -> it.markdowns.add(Markdown(file.readText()))
                            TypeFile.CHECKLIST.value -> it.checklist.add(parseYmlFile(file, Checklist::class))
                        }
                    }
                }
            }

            HIERARCHY_SUB_ELEMENT -> {
                root.categories.forEach { element ->
                    element.children.forEach { child ->
                        if (child.path == pwd) {
                            when (getDelimiter(file.nameWithoutExtension)) {
                                TypeFile.SEGMENT.value -> child.markdowns.add(Markdown(file.readText()))
                                TypeFile.CHECKLIST.value -> child.checklist.add(parseYmlFile(file, Checklist::class))
                            }
                        }
                    }
                }
            }
            HIERARCHY_CHILD -> {
                root.categories.forEach { element ->
                    element.children.forEach { child ->
                        child.children.forEach { grandchild ->
                            if (grandchild.path == pwd) {
                                when (getDelimiter(file.nameWithoutExtension)) {
                                    TypeFile.SEGMENT.value -> grandchild.markdowns.add(Markdown(file.readText()))
                                    TypeFile.CHECKLIST.value -> grandchild.checklist.add(parseYmlFile(file, Checklist::class))
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun addForms(file: File) {
        root.forms.add(parseYmlFile(file, Form::class))
    }
}