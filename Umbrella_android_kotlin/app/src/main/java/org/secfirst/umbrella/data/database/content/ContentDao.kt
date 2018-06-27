package org.secfirst.umbrella.data.database.content

import android.util.Log
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction
import org.secfirst.umbrella.data.Category
import org.secfirst.umbrella.data.Lesson
import org.secfirst.umbrella.data.database.AppDatabase


interface ContentDao {

    fun insert(lesson: Lesson) {
//        modelAdapter<Element>().load(lesson.elements.last())

//        FlowManager.getDatabase(AppDatabase::class.java).executeTransaction { databaseWrapper ->
//            lesson.elements.last().save(databaseWrapper)
//        }
        lesson.categories.forEach { category ->
            category.children.forEach { subcategory ->
                subcategory.subcategory = category
            }
        }
        FlowManager.getDatabase(AppDatabase.NAME)
                .beginTransactionAsync(ProcessModelTransaction.Builder<Category>(
                        ProcessModelTransaction.ProcessModel<Category> { model, wrapper_ ->
                            modelAdapter<Category>().save(model)

                        }).addAll(lesson.categories).build())
                .error { _, error -> Log.e("error", "error try to save $error") }
                .success { Log.e("error", "save with success") }.build().execute()
    }

    fun getAllContent() = Lesson(SQLite.select().from(Category::class.java).queryList())
}