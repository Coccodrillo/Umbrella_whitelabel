package org.secfirst.core.storage

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder

data class Root(val elements: MutableList<Element> = arrayListOf())

data class Element(var index: Int = 0,
                   var title: String = "",
                   var description: String = "",
                   @JsonIgnoreProperties var markDowns: MutableList<String> = arrayListOf(),
                   @JsonIgnoreProperties var children: MutableList<Element> = arrayListOf(),
                   @JsonIgnoreProperties var forms: MutableList<Form> = arrayListOf(),
                   @JsonIgnoreProperties var checklist: MutableList<CheckList> = arrayListOf(),
                   @JsonIgnoreProperties var rootDir: String = "",
                   @JsonIgnoreProperties var path: String = "")

class Form(content: MutableList<Content> = arrayListOf(), index: Long) {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder("list")
    class Content(val text: String = "", val label: String = "")
}


class CheckList(content: MutableList<Content> = arrayListOf(), index: Int) {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder("list")
    class Content(val text: String = "", val label: String = "")
}


