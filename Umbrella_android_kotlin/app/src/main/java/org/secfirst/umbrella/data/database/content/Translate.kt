package org.secfirst.umbrella.data.database.content

import org.secfirst.umbrella.data.Element
import org.secfirst.umbrella.data.Lesson

class Translate {

    fun test(lesson: Lesson): New {
        val categories: MutableList<Category> = mutableListOf()
        var subCategories: MutableList<Subcategory> = mutableListOf()
        var children: MutableList<Child> = mutableListOf()

        lesson.categories.forEach { element ->
            val category = populateCategory(element)
            categories.add(category)
            element.children.forEach { subElement ->

                val subCategory = populateSubcategory(subElement)
                subCategories.add(subCategory)
                subElement.children.forEach { subElementChild ->

                    val child = populateChild(subElementChild)
                    children.add(child)
                }
                subCategory.children = children
                children = mutableListOf()
            }
            category.subCategories = subCategories
            subCategories = mutableListOf()
        }
        return New(categories)
    }

    private fun populateCategory(element: Element): Category {
        val category = Category()
        category.checklist = element.checklist
        category.index = element.index
        category.description = element.description
        category.markdowns = element.markdowns
        category.path = element.path
        category.rootDir = element.rootDir
        category.title = element.title
        return category
    }

    private fun populateSubcategory(element: Element): Subcategory {
        val subcategory = Subcategory()
        subcategory.checklist = element.checklist
        subcategory.index = element.index
        subcategory.description = element.description
        subcategory.markdowns = element.markdowns
        subcategory.path = element.path
        subcategory.rootDir = element.rootDir
        subcategory.title = element.title
        return subcategory
    }

    private fun populateChild(element: Element): Child {
        val child = Child()
        child.checklist = element.checklist
        child.index = element.index
        child.description = element.description
        child.markdowns = element.markdowns
        child.path = element.path
        child.rootDir = element.rootDir
        child.title = element.title
        return child
    }
}