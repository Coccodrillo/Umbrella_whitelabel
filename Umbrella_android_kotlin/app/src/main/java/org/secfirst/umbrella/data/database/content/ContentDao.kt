package org.secfirst.umbrella.data.database.content

import android.util.Log
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction
import org.secfirst.umbrella.data.Category
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Lesson
import org.secfirst.umbrella.data.database.AppDatabase


interface ContentDao {

    fun insert(lesson: Lesson) {
        FlowManager.getDatabase(AppDatabase.NAME)
                .beginTransactionAsync(ProcessModelTransaction.Builder<Category>(
                        ProcessModelTransaction.ProcessModel<Category> { model, _ ->
                            model.associateForeignKey(model)
                            modelAdapter<Category>().save(model)
                            model.save()

                        }).addAll(lesson.categories).build())
                .error { _, error -> Log.e("error", "error try to save $error") }
                .success { Log.e("error", "save with success") }.build().execute()

        insertForms(lesson.forms)
    }

    private fun insertForms(forms: MutableList<Form>) {
        FlowManager.getDatabase(AppDatabase.NAME)
                .beginTransactionAsync(ProcessModelTransaction.Builder<Form>(
                        ProcessModelTransaction.ProcessModel<Form> { model, _ ->
                            model.associateFormForeignKey(forms)
                            modelAdapter<Form>().save(model)

                        }).addAll(forms).build())
                .error { _, error -> Log.e("error", "error try to save $error") }
                .success { Log.e("error", "save forms with success") }.build().execute()
    }

    fun getAllContent() = Lesson(SQLite.select().from(Category::class.java).queryList())
}