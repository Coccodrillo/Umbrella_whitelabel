package org.secfirst.whitelabel.data.database.content

import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import org.secfirst.whitelabel.data.*

interface ContentDao {

    fun insertAllLessons(root: Root) {

        val dataLesson = root.convertRootToLesson()

        dataLesson.categories.forEach { category ->
            category.associateForeignKey(category)
            modelAdapter<Category>().save(category)
        }

        dataLesson.categories.walkChild { child ->
            modelAdapter<Child>().save(child)
            insertChecklistContent(child.checklist)
        }

        insertForms(root.forms)
    }

    private fun insertChecklistContent(checklist: MutableList<Checklist>) {
        checklist.forEach { it ->
            it.content.forEach { content ->
                modelAdapter<Content>().save(content)
            }
        }
    }

    private fun insertForms(forms: MutableList<Form>) {
        forms.forEach { form ->
            form.associateFormForeignKey(forms)
            modelAdapter<Form>().save(form)
        }

        forms.forEach { form ->
            form.screens.forEach { screen ->
                screen.items.forEach { item ->
                    modelAdapter<Item>().save(item)
                }
            }
        }
    }


    fun getContents() = Lesson(SQLite.select().from(Category::class.java).queryList())
}