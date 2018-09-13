package org.secfirst.umbrella.whitelabel.data.database.lesson

import org.secfirst.umbrella.whitelabel.data.database.content.Category

interface LessonRepo {

    suspend fun loadAllCategories(): List<Category>
}