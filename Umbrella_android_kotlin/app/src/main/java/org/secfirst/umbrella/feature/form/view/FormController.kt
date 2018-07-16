package org.secfirst.umbrella.feature.form.view

import android.content.Context
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
import org.secfirst.umbrella.feature.form.view.adapter.FormAdapter
import javax.inject.Inject


class FormController : BaseController(), FormBaseView {

    @Inject
    internal lateinit var presenter: FormBasePresenter<FormBaseView, FormBaseInteractor>

    private var context: Context = UmbrellaApplication.instance

    override fun onInject() {
        DaggerFormComponent.builder().build().inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        ActiveFormRecycleView.layoutManager = LinearLayoutManager(context)
        allFormRecycleView_.layoutManager = LinearLayoutManager(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.form_view, container, false)
        presenter.onAttach(this)
        presenter.loadForms()

        return view
    }

    override fun showForms(forms: List<Form>) {
        forms.forEach { Log.e("test", "forms -  $it") }
        allFormRecycleView_.adapter = FormAdapter(forms, context)
    }

    override fun showProgress() {}

    override fun hideProgress() {}
}