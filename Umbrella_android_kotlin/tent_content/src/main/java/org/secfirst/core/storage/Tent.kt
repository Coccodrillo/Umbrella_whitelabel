package org.secfirst.core.storage

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder


data class Lesson(var categories: MutableList<Category> = arrayListOf())

data class Category(var markDown: String = "",
                    var description: String = "",
                    var title: String = "",
                    var index: Int = 0,
                    var path: String = "",
                    var rootDir: String = "",
                    var subcategories: MutableList<Category> = arrayListOf())

data class Segment(val markDown: String)

data class Subcategoy(val index: Int, val title: String, val description: String, val segments: MutableList<Segment>)

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


