package org.secfirst.umbrella.data

import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import org.secfirst.umbrella.data.database.content.Category
import org.secfirst.umbrella.data.database.content.Child

open class BaseModel : BaseRXModel() {
    fun associateForeignKey(category: Category) {

        category.subCategories.forEach { subcategory ->
            subcategory.category = category
            subcategory.children.forEach { child ->
                child.subcategory = subcategory
                associateChecklist(child.checklist, child)
            }
        }
    }

    private fun associateChecklist(checklists: MutableList<Checklist>, foreignKey: Child) {
        checklists.forEach { checklist ->
            checklist.child = foreignKey
            checklist.content.forEach { content ->
                content.checklist = checklist
            }
        }
    }

    fun associateFormForeignKey(forms: MutableList<Form>) {
        forms.forEach { form ->
            form.screens.forEach { screen ->
                screen.form = form
                screen.items.forEach { item ->
                    item.screen = screen
                    item.options.forEach { option -> option.item = item }
                }
            }
        }
    }
}