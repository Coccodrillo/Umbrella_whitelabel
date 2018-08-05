package org.secfirst.whitelabel.data.database.content

import org.secfirst.whitelabel.data.Root


interface ContentRepo {

    fun insertAllLessons(root: Root)

    fun loadLessons(): Lesson

}