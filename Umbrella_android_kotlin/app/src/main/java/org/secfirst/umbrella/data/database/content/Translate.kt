package org.secfirst.umbrella.data.database.content

import org.secfirst.umbrella.data.Root
import org.secfirst.umbrella.data.convertToCategory
import org.secfirst.umbrella.data.convertToChild
import org.secfirst.umbrella.data.convertToSubCategory

class Translate {

    fun test(root: Root): Lesson {
        val categories: MutableList<Category> = mutableListOf()
        var subCategories: MutableList<Subcategory> = mutableListOf()
        var children: MutableList<Child> = mutableListOf()

        root.categories.forEach { element ->
            val category = element.convertToCategory
            categories.add(category)
            element.children.forEach { subElement ->

                val subCategory = subElement.convertToSubCategory
                subCategories.add(subCategory)
                subElement.children.forEach { subElementChild ->

                    val child = subElementChild.convertToChild
                    children.add(child)
                }
                subCategory.children = children
                children = mutableListOf()
            }
            category.subCategories = subCategories
            subCategories = mutableListOf()
        }
        return Lesson(categories)
    }
}