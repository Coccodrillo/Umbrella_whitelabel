package org.secfirst.content.storage

import com.fasterxml.jackson.annotation.JsonProperty


class Root internal constructor(val elements: MutableList<Element> = arrayListOf(), val forms: MutableList<Form> = arrayListOf())

internal data class Element(
        var index: Int = 0,
        var title: String = "",
        var description: String = "",
        var markdowns: MutableList<Markdown> = arrayListOf(),
        var children: MutableList<Element> = arrayListOf(),
        var forms: MutableList<Form> = arrayListOf(),
        var checklist: MutableList<CheckList> = arrayListOf(),
        var rootDir: String = "",
        var path: String = "")

internal data class Markdown(var text: String = "")


internal class CheckList(
        @JsonProperty("list")
        var content: MutableList<Content> = arrayListOf())

internal class Content(
        var check: String = "",
        var checklist: CheckList? = null,
        var label: String = "")

internal data class Form(var screens: MutableList<Screen> = arrayListOf())


internal data class Screen(
        var name: String = "",
        var form: Form? = null,
        var items: MutableList<Item> = arrayListOf())

internal data class Item(
        var name: String = "",
        var type: String = "",
        var label: String = "",
        var screen: Screen? = null,
        var options: MutableList<Option> = arrayListOf(),
        var hint: String = "")

internal data class Option(
        var id: Long = 1,
        var label: String = "",
        var item: Item? = null,
        var value: String = "")



