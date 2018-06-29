package org.secfirst.umbrella.data.database.content

import android.util.Log
import com.raizlabs.android.dbflow.config.FlowManager
import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction
import org.secfirst.umbrella.data.*
import org.secfirst.umbrella.data.database.AppDatabase


interface ContentDao {

    fun insert(lesson: Lesson) {
        val dataLesson = Translate().test(lesson)
        FlowManager.getDatabase(AppDatabase.NAME)
                .beginTransactionAsync(ProcessModelTransaction.Builder<org.secfirst.umbrella.data.database.content.Category>(
                        ProcessModelTransaction.ProcessModel<org.secfirst.umbrella.data.database.content.Category> { model, _ ->
                            model.associateForeignKey(model)
                            modelAdapter<org.secfirst.umbrella.data.database.content.Category>().save(model)

                        }).addAll(dataLesson.categories).build())
                .error { _, error -> Log.e("error", "error try to save $error") }
                .success { Log.e("error", "save with success") }.build().execute()


        dataLesson.categories.forEach { category ->
            category.subCategories.forEach { subCategory ->
                subCategory.children.forEach { child ->
                    FlowManager.getDatabase(AppDatabase.NAME)
                            .beginTransactionAsync(ProcessModelTransaction.Builder<Child>(
                                    ProcessModelTransaction.ProcessModel<Child> { model, _ ->
                                        modelAdapter<Child>().save(model)
                                        insertChecklistContent(child.checklist)
                                    }).addAll(child).build())
                            .error { _, error -> Log.e("error", "error try to save $error") }
                            .success { Log.e("error", "save with success") }.build().execute()
                }
            }
        }
        insertForms(lesson.forms)
    }

    private fun insertChecklistContent(checklist: MutableList<Checklist>) {

        checklist.forEach { checklist ->
            checklist.content.forEach { content ->
                FlowManager.getDatabase(AppDatabase.NAME)
                        .beginTransactionAsync(ProcessModelTransaction.Builder<Content>(
                                ProcessModelTransaction.ProcessModel<Content> { model, _ ->
                                    modelAdapter<Content>().save(model)

                                }).addAll(content).build())
                        .error { _, error -> Log.e("error", "error try to save $error") }
                        .success { Log.e("error", "save forms with success") }.build().execute()
            }
        }
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

        forms.forEach { form ->
            form.screens.forEach { screen ->
                screen.items.forEach { item ->
                    FlowManager.getDatabase(AppDatabase.NAME)
                            .beginTransactionAsync(ProcessModelTransaction.Builder<Item>(
                                    ProcessModelTransaction.ProcessModel<Item> { model, _ ->
                                        modelAdapter<Item>().save(model)
                                    }).addAll(item).build())
                            .error { _, error -> Log.e("error", "error try to save $error") }
                            .success { Log.e("error", "save item with success") }.build().execute()
                }
            }
        }
    }

    fun getAllContent() = New(SQLite.select().from(Category::class.java).queryList())
}