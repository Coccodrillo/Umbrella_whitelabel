package org.secfirst.umbrella.data.database.content

import org.secfirst.umbrella.data.Category
import org.secfirst.umbrella.data.Lesson


interface ContentRepo {

    fun insertAllLessons(lesson: Lesson)

    fun getAllLessons(): MutableList<Category>

}