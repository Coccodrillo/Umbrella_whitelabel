package org.secfirst.umbrella.data.database.form

import org.secfirst.umbrella.data.Answer
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Screen
import javax.inject.Inject

class FormRepository @Inject constructor(private val formDao: FormDao) : FormRepo {

    override suspend fun loadFormIdBy(title: String): Long = formDao.getFormIdBy(title)!!

    override suspend fun loadScreenBy(formId: Long): List<Screen> = formDao.getScreenBy(formId)

    override suspend fun persistForm(form: Form) = formDao.insertForm(form)

    override suspend fun persistFormData(answer: Answer) = formDao.insertDataForm(answer)

    override suspend fun loadModelForms() = formDao.getAllFormModel()

    override suspend fun loadAnswerBy(formId: Long) = formDao.getAnswerBy(formId)

    override suspend fun loadActiveForms(): List<Form> = formDao.getAllActiveForms()

}