package org.secfirst.umbrella.data.database.form

import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value
import javax.inject.Inject

class FormRepository @Inject constructor(private val formDao: FormDao) : FormRepo {

    override suspend fun persist(formData: Value) = formDao.insertDataForm(formData)

    override suspend fun getAll() = formDao.getAllFormModel()

    override suspend fun loadBy(id: Long) = formDao.getDataFormById(id)

    override suspend fun loadDataBy(forms: List<Form>) = formDao.getAllDataFormBy(forms)

}