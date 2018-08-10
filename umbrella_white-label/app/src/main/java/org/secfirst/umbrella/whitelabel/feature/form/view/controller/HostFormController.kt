package org.secfirst.umbrella.whitelabel.feature.form.view.controller

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.RouterTransaction
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter
import kotlinx.android.synthetic.main.host_form_view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.UmbrellaApplication
import org.secfirst.umbrella.whitelabel.data.Form
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController
import org.secfirst.umbrella.whitelabel.feature.form.DaggerFormComponent
import org.secfirst.umbrella.whitelabel.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.form.presenter.FormBasePresenter
import org.secfirst.umbrella.whitelabel.feature.form.view.FormView
import org.secfirst.umbrella.whitelabel.feature.form.view.adapter.ActiveFormSection
import org.secfirst.umbrella.whitelabel.feature.form.view.adapter.AllFormSection
import javax.inject.Inject


class HostFormController : BaseController(), FormView {

    @Inject
    internal lateinit var presenter: FormBasePresenter<FormView, FormBaseInteractor>
    private val editClick: (Form) -> Unit = this::onEditFormClicked
    private val deleteClick: (Int, Form) -> Unit = this::onDeleteFormClicked
    private val shareClick: (Form) -> Unit = this::onShareFormClicked
    private val sectionAdapter: SectionedRecyclerViewAdapter by lazy { SectionedRecyclerViewAdapter() }
    private var allFormTag = ""
    private var activeFormTag = ""
    private lateinit var allFormSection: AllFormSection
    private lateinit var activeFormSection: ActiveFormSection

    override fun onInject() {
        DaggerFormComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.onAttach(this)
        allFormRecycleView.layoutManager = LinearLayoutManager(view.context)
        presenter.submitLoadAllForms()
    }

    private fun onEditFormClicked(form: Form) {
        router.pushController(RouterTransaction.with(FormController(form)))
    }

    private fun onDeleteFormClicked(position: Int, form: Form) {
        activeFormSection.remove(position, sectionAdapter)
        presenter.submitDeleteForm(form)
    }

    private fun onShareFormClicked(form: Form) {
        router.pushController(RouterTransaction.with(FormController(form)))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.host_form_view, container, false)
    }

    override fun showModelAndActiveForms(modelForms: List<Form>, activeForms: List<Form>) {
        sectionAdapter.removeAllSections()
        sectionAdapter.notifyDataSetChanged()

        resources?.let {
            allFormTag = it.getString(R.string.message_title_all_forms)
            activeFormTag = it.getString(R.string.message_title_active_forms)
        }

        allFormRecycleView?.let {
            it.adapter = sectionAdapter

            allFormSection = AllFormSection(editClick, allFormTag, modelForms.toMutableList())
            activeFormSection = ActiveFormSection(editClick, deleteClick, shareClick, activeFormTag, activeForms.toMutableList())

            sectionAdapter.addSection(allFormSection)
            sectionAdapter.addSection(activeFormSection)
        }
    }

    override fun getTitleToolbar() = applicationContext?.getString(R.string.form_title)!!

    override fun getEnableBackAction() = false
}