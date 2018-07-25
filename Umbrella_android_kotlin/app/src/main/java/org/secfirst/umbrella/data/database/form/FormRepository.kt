package org.secfirst.umbrella.data.database.form

import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value
import javax.inject.Inject

class FormRepository @Inject constructor(private val formDao: FormDao) : FormRepo {

    override fun loadDataFormsById(id: Long): List<Value> = formDao.getDataFormById(id)

    override fun persistDataForm(formData: Value) = formDao.insertDataForm(formData)

    override fun getAllModelForms(): List<Form> = formDao.getAllModel()
}