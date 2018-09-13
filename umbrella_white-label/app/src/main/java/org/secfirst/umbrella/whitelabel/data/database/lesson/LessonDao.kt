package org.secfirst.umbrella.whitelabel.data.database.lesson

import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.coroutines.experimental.withContext
import org.secfirst.umbrella.whitelabel.data.database.content.Category
import org.secfirst.umbrella.whitelabel.misc.AppExecutors

interface LessonDao {

    suspend fun getAllLesson(): List<Category> = withContext(AppExecutors.ioContext) {
        SQLite.select()
                .from(Category::class.java)
                .queryList()
    }
}