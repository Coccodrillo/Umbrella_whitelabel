package org.secfirst.umbrella.feature.form.interactor

import io.reactivex.Single
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value
import org.secfirst.umbrella.data.database.form.FormRepo
import org.secfirst.umbrella.feature.base.interactor.BaseInteractorImp
import javax.inject.Inject

class FormInteractorImp @Inject constructor(private val formRepo: FormRepo) : BaseInteractorImp(), FormBaseInteractor {

    override fun saveForm(formData: Value) = formRepo.persistDataForm(formData)

    override fun fetchForm(): Single<List<Form>> = formRepo.getAllModelForms()
}