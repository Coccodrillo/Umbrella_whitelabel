package org.secfirst.umbrella.data.database.content

import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import io.reactivex.Single


interface ContentDao {

    fun insert(root: Lesson) = modelAdapter<Category>().saveAll(root.categories)

    fun getAllContent() = Single.just(Lesson(SQLite.select().from(Category::class.java).queryList()))
}