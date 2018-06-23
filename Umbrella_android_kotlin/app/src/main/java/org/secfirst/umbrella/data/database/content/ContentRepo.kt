package org.secfirst.umbrella.data.database.content

import io.reactivex.Single


interface ContentRepo {

    fun insertAllLessons(lesson: Lesson)

    fun getAllLessons(): Single<Lesson>

}