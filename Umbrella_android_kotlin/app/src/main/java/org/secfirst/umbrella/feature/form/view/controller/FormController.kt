package org.secfirst.umbrella.feature.form.view.controller

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.form_view.*
import org.secfirst.umbrella.R
import org.secfirst.umbrella.UmbrellaApplication
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.feature.base.view.BaseController
import org.secfirst.umbrella.feature.form.DaggerFormComponent
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.presenter.FormBasePresenter
import org.secfirst.umbrella.feature.form.view.FormView
import org.secfirst.umbrella.feature.form.view.adapter.ActiveFormAdapter
import org.secfirst.umbrella.feature.form.view.adapter.AllFormAdapter
import javax.inject.Inject


class FormController : BaseController(), FormView {

    @Inject
    internal lateinit var presenter: FormBasePresenter<FormView, FormBaseInteractor>
    private var application = UmbrellaApplication.instance


    override fun onInject() {
        DaggerFormComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.onAttach(this)
        activeFormRecycleView.layoutManager = LinearLayoutManager(application)
        allFormRecycleView.layoutManager = LinearLayoutManager(application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        presenter.submitLoadModelForms()
        presenter.submitLoadActiveForms()
        return inflater.inflate(R.layout.form_view, container, false)
    }

    override fun showModelForms(modelForms: List<Form>) {
        modelForms.forEach { Log.e("test", "modelForms -  $it") }
        allFormRecycleView.adapter = AllFormAdapter(modelForms, router)
    }

    override fun showActiveForms(activeForms: List<Form>) {
        activeFormRecycleView.adapter = ActiveFormAdapter(activeForms, router)
    }
}