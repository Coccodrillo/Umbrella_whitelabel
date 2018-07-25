package org.secfirst.umbrella.data.database.form

import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value

interface FormRepo {

    fun persistDataForm(formData: Value)

    fun getAllModelForms(): List<Form>

    fun loadDataFormsById(id: Long): List<Value>
}