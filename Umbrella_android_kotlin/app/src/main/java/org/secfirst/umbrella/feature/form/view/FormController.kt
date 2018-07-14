package org.secfirst.umbrella.feature.form.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.secfirst.umbrella.R
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.feature.base.view.BaseController
import org.secfirst.umbrella.feature.form.DaggerFormComponent
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.presenter.FormBasePresenter
import javax.inject.Inject

class FormController : BaseController(), FormBaseView {

    override fun onInject() {
        DaggerFormComponent.builder().build().inject(this)
    }

    @Inject
    internal lateinit var presenter: FormBasePresenter<FormBaseView, FormBaseInteractor>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.fragment_form, container, false)
        presenter.onAttach(this)
        presenter.loadForms()
        return view
    }

    override fun showForms(forms: List<Form>) {
        forms.forEach { Log.e("test", "forms -  $it") }
    }

    override fun showProgress() {}

    override fun hideProgress() {}
}