package org.secfirst.umbrella.data.database.form

import org.secfirst.umbrella.data.Answer
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Screen

interface FormRepo {

    suspend fun persistFormData(answer: Answer)

    suspend fun persistForm(form: Form)

    suspend fun loadModelForms(): List<Form>

    suspend fun loadAnswerBy(formId: Long): List<Answer>

    suspend fun loadActiveForms(): List<Form>

    suspend fun loadScreenBy(formId: Long): List<Screen>


    suspend fun loadFormIdBy(title : String): Long

}