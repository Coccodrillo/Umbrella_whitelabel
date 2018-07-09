package org.secfirst.umbrella.feature.form.interactor

import io.reactivex.Single
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.database.content.ContentRepo
import org.secfirst.umbrella.feature.base.interactor.BaseInteractorImp
import javax.inject.Inject

class FormInteractorImp @Inject constructor(private val contentRepo: ContentRepo) : BaseInteractorImp(), FormBaseInteractor {

    override fun fetchForm(): Single<List<Form>> = contentRepo.getForms()
}