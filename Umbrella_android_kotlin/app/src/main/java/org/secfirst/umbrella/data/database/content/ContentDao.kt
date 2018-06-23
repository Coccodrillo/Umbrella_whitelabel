package org.secfirst.umbrella.data.database.content

import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import org.secfirst.umbrella.data.Category
import org.secfirst.umbrella.data.Lesson


interface ContentDao {

    fun insert(root: Lesson) = modelAdapter<Category>().saveAll(root.categories)

    fun getAllContent() = SQLite.select().from(Category::class.java).queryList()
}