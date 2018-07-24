package org.secfirst.umbrella.feature.form.interactor

import io.reactivex.Single
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value
import org.secfirst.umbrella.feature.base.interactor.BaseInteractor


interface FormBaseInteractor : BaseInteractor {

    fun fetchForm(): Single<List<Form>>

    fun saveForm(formData : Value)

}