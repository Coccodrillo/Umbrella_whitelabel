package org.secfirst.umbrella.feature.form.interactor

import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value
import org.secfirst.umbrella.feature.base.interactor.BaseInteractor


interface FormBaseInteractor : BaseInteractor {

    suspend fun fetchForm(): List<Form>

    suspend fun persisteFormData(formData: Value)

    suspend fun loadDataFormBy(id: Long): List<Value>

    suspend fun loadDataFormBy(forms: List<Form>): List<Form>

}