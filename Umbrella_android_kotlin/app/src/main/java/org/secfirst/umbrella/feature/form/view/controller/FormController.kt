package org.secfirst.umbrella.feature.form.view.controller

import android.support.design.widget.BottomNavigationView
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
import org.secfirst.umbrella.feature.form.view.adapter.FormAdapter
import javax.inject.Inject


class FormController : BaseController(), FormView {

    @Inject
    internal lateinit var presenter: FormBasePresenter<FormView, FormBaseInteractor>
    private val application = UmbrellaApplication.instance

    override fun onInject() {
        DaggerFormComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        ActiveFormRecycleView.layoutManager = LinearLayoutManager(application)
        allFormRecycleView.layoutManager = LinearLayoutManager(application)
        presenter.onAttach(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.form_view, container, false)
        presenter.onAttach(this)
        presenter.submitLoadModelForms()
        return view
    }

    override fun showModelForms(forms: List<Form>) {
        forms.forEach { Log.e("test", "forms -  $it") }
        allFormRecycleView.adapter = FormAdapter(forms, router)
    }
}