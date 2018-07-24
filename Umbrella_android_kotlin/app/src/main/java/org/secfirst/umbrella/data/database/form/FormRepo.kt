package org.secfirst.umbrella.data.database.form

import io.reactivex.Single
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value

interface FormRepo {

    fun persistDataForm(formData : Value)

    fun getAllModelForms(): Single<List<Form>>
}