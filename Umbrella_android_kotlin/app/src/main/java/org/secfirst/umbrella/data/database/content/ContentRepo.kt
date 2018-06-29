package org.secfirst.umbrella.data.database.content

import org.secfirst.umbrella.data.Root


interface ContentRepo {

    fun insertAllLessons(root: Root)

    fun loadAllLesson(): Lesson

}