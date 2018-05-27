package org.secfirst.umbrella.data.internal

data class Category(val title: String, val index: Long, val subcategories: List<SubCategory>?)

data class SubCategory(val title: String, val index: Long)