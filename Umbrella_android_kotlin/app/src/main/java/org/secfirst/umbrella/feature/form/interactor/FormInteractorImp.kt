package org.secfirst.umbrella.feature.form.interactor

import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value
import org.secfirst.umbrella.data.database.form.FormRepo
import org.secfirst.umbrella.feature.base.interactor.BaseInteractorImp
import javax.inject.Inject

class FormInteractorImp @Inject constructor(private val formRepo: FormRepo) : BaseInteractorImp(), FormBaseInteractor {

    override suspend fun loadDataFormBy(forms: List<Form>) = formRepo.loadDataBy(forms)

    override suspend fun loadDataFormBy(id: Long) = formRepo.loadBy(id)

    override suspend fun persisteFormData(formData: Value) = formRepo.persist(formData)

    override suspend fun fetchForm() = formRepo.getAll()
}