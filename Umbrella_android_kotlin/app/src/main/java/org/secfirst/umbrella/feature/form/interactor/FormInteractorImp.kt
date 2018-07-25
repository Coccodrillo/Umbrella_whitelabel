package org.secfirst.umbrella.feature.form.interactor

import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value
import org.secfirst.umbrella.data.database.form.FormRepo
import org.secfirst.umbrella.feature.base.interactor.BaseInteractorImp
import javax.inject.Inject

class FormInteractorImp @Inject constructor(private val formRepo: FormRepo) : BaseInteractorImp(), FormBaseInteractor {

    override fun loadDataFormById(id: Long): List<Value> = formRepo.loadDataFormsById(id)

    override fun saveForm(formData: Value) = formRepo.persistDataForm(formData)

    override fun fetchForm(): List<Form> = formRepo.getAllModelForms()
}