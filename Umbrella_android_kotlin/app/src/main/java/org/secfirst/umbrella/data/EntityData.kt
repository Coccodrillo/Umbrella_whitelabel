package org.secfirst.umbrella.data

import com.fasterxml.jackson.annotation.JsonProperty
import com.raizlabs.android.dbflow.annotation.*
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import com.raizlabs.android.dbflow.sql.language.SQLite
import io.reactivex.Single
import org.secfirst.umbrella.data.database.AppDatabase


class Lesson(val categories: MutableList<Category> = arrayListOf(), val forms: MutableList<Form> = arrayListOf())

@Table(database = AppDatabase::class)
data class Category(
        @PrimaryKey(autoincrement = true)
        var id: Long = 0,
        @ForeignKey(stubbedRelationship = true)
        var category: Category? = null,
        @Column
        var index: Int = 0,
        @Column
        var title: String = "",
        @Column
        var description: String = "",
        var markdowns: MutableList<Markdown> = arrayListOf(),
        var children: MutableList<Category> = arrayListOf(),
        var forms: MutableList<Form> = arrayListOf(),
        var checklist: MutableList<CheckList> = arrayListOf(),
        @Column
        var rootDir: String = "",
        @Column
        var path: String = "") : BaseRXModel() {

    override fun save(): Single<Boolean> {
        val res = super.save()
        if (markdowns.isEmpty()) {
            for (markdown in markdowns) {
                markdown.category = this
                markdown.save()
            }
        }
        if (children.isEmpty()) {
            for (children in children) {
                children.category = this
                children.save()
            }
        }
        if (forms.isEmpty()) {
            for (forms in forms) {
                forms.category = this
                forms.save()
            }
        }
        if (checklist.isEmpty()) {
            for (checklist in checklist) {
                checklist.category = this
                checklist.save()
            }
        }
        return res
    }

    @OneToMany(methods = [(OneToMany.Method.ALL)], variableName = "markdowns")
    fun oneToManyMarkdowns(): MutableList<Markdown> {
        if (markdowns.isEmpty()) {
            markdowns = SQLite.select()
                    .from(Markdown::class.java)
                    .where(Markdown_Table.category_id.eq(id))
                    .queryList()
        }
        return markdowns
    }

    @OneToMany(methods = [(OneToMany.Method.ALL)], variableName = "children")
    fun oneToManyChildren(): MutableList<Category> {
        if (children.isEmpty()) {
            children = SQLite.select()
                    .from(Category::class.java)
                    .where(Category_Table.category_id.eq(id))
                    .queryList()
        }
        return children
    }

    @OneToMany(methods = [(OneToMany.Method.ALL)], variableName = "forms")
    fun oneToManyForms(): MutableList<Form> {
        if (forms.isEmpty()) {
            forms = SQLite.select()
                    .from(Form::class.java)
                    .where(Form_Table.category_id.eq(id))
                    .queryList()
        }
        return forms
    }

    @OneToMany(methods = [(OneToMany.Method.ALL)], variableName = "checklist")
    fun oneToManyChecklist(): MutableList<CheckList> {
        if (checklist.isEmpty()) {
            checklist = SQLite.select()
                    .from(CheckList::class.java)
                    .where(CheckList_Table.category_id.eq(id))
                    .queryList()
        }
        return checklist
    }
}

@Table(database = AppDatabase::class, allFields = true)
data class Markdown(
        @PrimaryKey(autoincrement = true)
        var id: Long = 0,
        @ForeignKey(stubbedRelationship = true)
        var category: Category? = null,
        var text: String = "") : BaseRXModel() {
    constructor(text: String) : this(0, null, text)
}

@Table(database = AppDatabase::class)
class CheckList(
        @PrimaryKey(autoincrement = true)
        var id: Long = 0,
        @Column
        var index: Int = 0,
        @ForeignKey(stubbedRelationship = true)
        var category: Category? = null,
        @JsonProperty("list")
        var content: MutableList<Content> = arrayListOf()) : BaseRXModel() {

    @OneToMany(methods = [(OneToMany.Method.ALL)], variableName = "content")
    fun oneToManyContent(): MutableList<Content> {
        if (content.isEmpty()) {
            content = SQLite.select()
                    .from(Content::class.java)
                    .where(Content_Table.checklist_id.eq(id))
                    .queryList()
        }
        return content
    }
}

@Table(database = AppDatabase::class, allFields = true)
class Content(
        @PrimaryKey(autoincrement = true)
        var id: Long = 0,
        var check: String = "",
        @ForeignKey(stubbedRelationship = true)
        var checklist: CheckList? = null,
        var label: String = "") : BaseRXModel()

@Table(database = AppDatabase::class)
data class Form(
        @PrimaryKey(autoincrement = true)
        var id: Long = 0,
        @ForeignKey(stubbedRelationship = true)
        var category: Category? = null,
        var screens: MutableList<Screen> = arrayListOf()) : BaseRXModel() {

    @OneToMany(methods = [(OneToMany.Method.ALL)], variableName = "screens")
    fun oneToManyScreens(): MutableList<Screen> {
        if (screens.isEmpty()) {
            screens = SQLite.select()
                    .from(Screen::class.java)
                    .where(Screen_Table.form_id.eq(id))
                    .queryList()
        }
        return screens
    }
}

@Table(database = AppDatabase::class)
data class Screen(
        @PrimaryKey(autoincrement = true)
        var id: Long = 1,
        @Column
        var name: String = "",
        @ForeignKey(stubbedRelationship = true)
        var form: Form? = null,
        var items: MutableList<Item> = arrayListOf()) : BaseRXModel() {

    @OneToMany(methods = [(OneToMany.Method.ALL)], variableName = "items")
    fun oneToManyItems(): MutableList<Item> {
        if (items.isEmpty()) {
            items = SQLite.select()
                    .from(Item::class.java)
                    .where(Item_Table.screen_id.eq(id))
                    .queryList()
        }
        return items
    }
}

@Table(database = AppDatabase::class)
data class Item(
        @PrimaryKey(autoincrement = true)
        var id: Long = 0,
        @Column
        var name: String = "",
        @Column
        var type: String = "",
        @Column
        var label: String = "",
        @ForeignKey(stubbedRelationship = true)
        var screen: Screen? = null,
        var options: MutableList<Option> = arrayListOf(),
        @Column
        var hint: String = "") : BaseRXModel() {

    @OneToMany(methods = [(OneToMany.Method.ALL)], variableName = "options")
    fun oneToManyOptions(): MutableList<Option> {
        if (options.isEmpty()) {
            options = SQLite.select()
                    .from(Option::class.java)
                    .where(Option_Table.item_id.eq(id))
                    .queryList()
        }
        return options
    }
}

@Table(database = AppDatabase::class, allFields = true)
data class Option(
        @PrimaryKey(autoincrement = true)
        var id: Long = 0,
        var label: String = "",
        @ForeignKey(stubbedRelationship = true)
        var item: Item? = null,
        var value: String = "") : BaseRXModel()



