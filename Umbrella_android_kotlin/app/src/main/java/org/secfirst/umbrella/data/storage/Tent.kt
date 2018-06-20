package org.secfirst.umbrella.data.storage

import com.fasterxml.jackson.annotation.JsonProperty
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import org.secfirst.umbrella.data.database.AppDatabase
import java.io.File


data class Root(val elements: MutableList<Element> = arrayListOf(), val forms: MutableList<Form> = arrayListOf())

@Table(database = AppDatabase::class)
data class Element(
        @PrimaryKey(autoincrement = true)
        var id: Long = 1,
        @Column
        var index: Int = 0,
        @Column
        var title: String = "",
        @Column
        var description: String = "",
        var markdowns: MutableList<File> = arrayListOf(),
        var children: MutableList<Element> = arrayListOf(),
        var forms: MutableList<Form> = arrayListOf(),
        var checklist: MutableList<CheckList> = arrayListOf(),
        @Column
        var rootDir: String = "",
        @Column
        var path: String = "")

@Table(database = AppDatabase::class)
class CheckList(
        @PrimaryKey(autoincrement = true)
        var id: Long = 1,
        @Column
        var index: Int = 0,
        @JsonProperty("list")
        var content: MutableList<Content> = arrayListOf())

@Table(database = AppDatabase::class)
data class Form(
        @PrimaryKey(autoincrement = true)
        var id: Long = 1,
        var screens: MutableList<Screen> = arrayListOf())

@Table(database = AppDatabase::class, allFields = true)
class Content(
        @PrimaryKey(autoincrement = true)
        var id: Long = 1,
        var check: String = "",
        var label: String = "")

@Table(database = AppDatabase::class)
data class Screen(
        @PrimaryKey(autoincrement = true)
        var id: Long = 1,
        @Column
        var name: String = "",
        var items: MutableList<Item> = arrayListOf())

@Table(database = AppDatabase::class)
data class Item(
        @PrimaryKey(autoincrement = true)
        var id: Long = 1,
        @Column
        var name: String = "",
        @Column
        var type: String = "",
        @Column
        var label: String = "",
        var options: MutableList<Option> = arrayListOf(),
        @Column
        var hint: String = "")


@Table(database = AppDatabase::class, allFields = true)
data class Option(
        @PrimaryKey(autoincrement = true)
        var id: Long = 1,
        var label: String = "",
        var value: String = "") : BaseRXModel()
