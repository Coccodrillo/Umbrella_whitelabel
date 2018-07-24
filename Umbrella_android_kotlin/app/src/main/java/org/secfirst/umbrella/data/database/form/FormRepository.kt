package org.secfirst.umbrella.data.database.form

import io.reactivex.Single
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value
import javax.inject.Inject

class FormRepository @Inject constructor(private val formDao: FormDao) : FormRepo {

    override fun persistDataForm(formData: Value) = formDao.insertDataForm(formData)

    override fun getAllModelForms(): Single<List<Form>> = formDao.getAllModel()
}