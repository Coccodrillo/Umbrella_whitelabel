package org.secfirst.umbrella.feature.form.view.controller

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import kotlinx.android.synthetic.main.host_form_view.*
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
import org.secfirst.umbrella.misc.initRecyclerView
import javax.inject.Inject


class HostFormController : BaseController(), FormView {

    @Inject
    internal lateinit var presenter: FormBasePresenter<FormView, FormBaseInteractor>
    private val editClick: (Form) -> Unit = this::onEditFormClicked
    private val deleteClick: (Form) -> Unit = this::onDeleteFormClicked
    private val shareClick: (Form) -> Unit = this::onShareFormClicked
    private val allFormAdapter = AllFormAdapter(editClick)
    private val activeFormAdapter = ActiveFormAdapter(editClick, deleteClick, shareClick)

    override fun onInject() {
        DaggerFormComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.onAttach(this)
        activeFormRecycleView.initRecyclerView(LinearLayoutManager(view.context), activeFormAdapter)
        allFormRecycleView.initRecyclerView(LinearLayoutManager(view.context), allFormAdapter)
        presenter.submitLoadModelForms()
        presenter.submitLoadActiveForms()
    }

    private fun onEditFormClicked(form: Form) {
        router.pushController(RouterTransaction.with(FormController(form))
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    private fun onDeleteFormClicked(form: Form) {
        presenter.submitDeleteForm(form)
        activeFormAdapter.remove(form)
    }

    private fun onShareFormClicked(form: Form) {
        router.pushController(RouterTransaction.with(FormController(form))
                .pushChangeHandler(FadeChangeHandler())
                .popChangeHandler(FadeChangeHandler()))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.host_form_view, container, false)
    }

    override fun showModelForms(modelForms: List<Form>) {
            allFormAdapter.updateForms(modelForms)
    }

    override fun showActiveForms(activeForms: List<Form>) {
        activeFormAdapter.updateForms(activeForms)
    }

    override fun getTitleToolbar() = applicationContext?.getString(R.string.form_title)!!

    override fun getEnableBackAction() = false
}