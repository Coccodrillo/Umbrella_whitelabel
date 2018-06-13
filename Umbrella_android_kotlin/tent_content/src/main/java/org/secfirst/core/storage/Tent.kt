package org.secfirst.core.storage

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.File


data class Root(val elements: MutableList<Element> = arrayListOf(), val forms: MutableList<Form> = arrayListOf())

data class Element(var index: Int = 0,
                   var title: String = "",
                   var description: String = "",
                   var markdowns: MutableList<File> = arrayListOf(),
                   var children: MutableList<Element> = arrayListOf(),
                   var forms: MutableList<Form> = arrayListOf(),
                   var checklist: MutableList<CheckList> = arrayListOf(),
                   var rootDir: String = "",
                   var path: String = "")

class CheckList(
        @JsonProperty("index") var index: Int,
        @JsonProperty("list") var content: MutableList<Content> = arrayListOf())


class Content(@JsonProperty("check") var check: String = "",
              @JsonProperty("label") var label: String = "")

data class Form(@JsonProperty("screens") var screens: MutableList<Screen> = arrayListOf())

data class Screen(
        @JsonProperty("name") var name: String = "",
        @JsonProperty("items") var items: MutableList<Item> = arrayListOf())

data class Item(
        @JsonProperty("name") var name: String = "",
        @JsonProperty("type") var type: String = "",
        @JsonProperty("label") var label: String = "",
        @JsonProperty("options") var options: MutableList<Option> = arrayListOf(),
        @JsonProperty("hint") var hint: String = "")

data class Option(var label: String = "", var value: String = "")