package org.secfirst.umbrella.data

import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel

open class BaseModel : BaseRXModel() {
    fun associateForeignKey(category: Category) {

        associateMarkdowns(category.markdowns, category)
        associateChecklist(category.checklist, category)
        category.children.forEach { subcategory ->
            subcategory.subcategory = category
            associateChecklist(subcategory.checklist, subcategory)
            associateMarkdowns(subcategory.markdowns, subcategory)
            subcategory.children.forEach { child ->
                child.subcategory = subcategory
                associateChecklist(child.checklist, child)
                associateMarkdowns(child.markdowns, child)
            }
        }
    }

    private fun associateMarkdowns(markdown: MutableList<Markdown>, foreignKey: Category) {
        markdown.forEach { it ->
            it.category = foreignKey
        }
    }

    private fun associateChecklist(checklists: MutableList<CheckList>, foreignKey: Category) {
        checklists.forEach { checklist ->
            checklist.category = foreignKey
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