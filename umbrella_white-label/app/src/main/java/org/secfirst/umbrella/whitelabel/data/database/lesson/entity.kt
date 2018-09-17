package org.secfirst.umbrella.whitelabel.data.database.lesson

import android.os.Parcelable
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup
import kotlinx.android.parcel.Parcelize
import org.secfirst.umbrella.whitelabel.data.database.content.Category
import org.secfirst.umbrella.whitelabel.data.database.content.Subcategory

@Parcelize
data class Difficult(val title: String, val description: String, val layoutColor: String, val titleToolbar: String) : Parcelable {
    companion object {
        const val BEGINNER = 1
        const val ADVANCED = 2
        const val EXPERT = 3
    }
}

class Lesson(subject: String, var pathIcon: String, topics: List<Topic>) : ExpandableGroup<Lesson.Topic>(subject, topics) {
    @Parcelize
    class Topic(var title: String = "", var idReference: Long = 0) : Parcelable
}

fun Subcategory.toDifficult(): MutableList<Difficult> {
    val difficulties = mutableListOf<Difficult>()
    val subcategorySorted = this.children.sortedWith(compareBy { it.index })
    subcategorySorted.forEach { child ->
        when (child.index) {
            Difficult.BEGINNER -> difficulties.add(Difficult(child.title, child.description, "#87BD34", this.title))
            Difficult.ADVANCED -> difficulties.add(Difficult(child.title, child.description, "#F3BC2B", this.title))
            Difficult.EXPERT -> difficulties.add(Difficult(child.title, child.description, "#B83657", this.title))
            else -> {
                difficulties.add(Difficult(child.title, child.description, "#B83657", this.title))
            }
        }
    }
    return difficulties
}

fun List<Category>.toLesson(): List<Lesson> {
    val itemSections = mutableListOf<Lesson>()
    this.forEach { category ->
        val itemGroups = mutableListOf<Lesson.Topic>()
        category.subcategories.forEach { subcategory ->
            val itemGroup = Lesson.Topic(subcategory.title, subcategory.id)
            itemGroups.add(itemGroup)
        }
        val itemSection = Lesson(category.title, category.resourcePath, itemGroups)
        itemSections.add(itemSection)
    }
    return itemSections
}