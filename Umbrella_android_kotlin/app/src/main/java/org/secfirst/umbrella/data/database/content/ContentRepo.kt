package org.secfirst.umbrella.data.database.content

import org.secfirst.umbrella.data.Lesson


interface ContentRepo {

    fun insertAllLessons(lesson: Lesson)

    fun loadAllLesson(): New

}