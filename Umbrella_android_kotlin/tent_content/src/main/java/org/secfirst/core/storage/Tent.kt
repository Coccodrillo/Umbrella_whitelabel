package org.secfirst.core.storage

import com.fasterxml.jackson.annotation.JsonProperty
import java.io.File


data class Root(val elements: MutableList<Element> = arrayListOf())

data class Element(var index: Int = 0,
                   var title: String = "",
                   var description: String = "",
                   var markdowns: MutableList<File> = arrayListOf(),
                   var children: MutableList<Element> = arrayListOf(),
                   var forms: MutableList<Form> = arrayListOf(),
                   var checklist: MutableList<CheckList> = arrayListOf(),
                   var rootDir: String = "",
                   var path: String = "")

class Form(var content: MutableList<Content> = arrayListOf(), var index: Long)


class CheckList(
        @JsonProperty("index") var index: Int,
        @JsonProperty("list") var content: MutableList<Content> = arrayListOf())


class Content(@JsonProperty("check") var check: String = "",
              @JsonProperty("label") var label: String = "")