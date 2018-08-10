package org.secfirst.umbrella.whitelabel.feature.form.view.controller

import android.content.Context
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
import org.secfirst.umbrella.whitelabel.feature.MainActivity
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
    private val deleteClick: (Form) -> Unit = this::onDeleteFormClicked
    private val shareClick: (Form) -> Unit = this::onShareFormClicked
    private lateinit var context: Context
    private val sectionAdapter: SectionedRecyclerViewAdapter by lazy { SectionedRecyclerViewAdapter() }


    override fun onInject() {
        DaggerFormComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.onAttach(this)
        //activeFormRecycleView.initRecyclerView(LinearLayoutManager(view.context), activeFormAdapter)
        //allFormRecycleView.initRecyclerView(LinearLayoutManager(view.context), sectionAdapter)
        allFormRecycleView.layoutManager = LinearLayoutManager(view.context)
        activity?.let { context = it }
        val mainActivity = activity as MainActivity
//        activeFormRecycleView.addOnScrollListener(object : HideShowScrollListener(context) {
//            override fun onMoved(distance: Int) {
//                mainActivity.test.translationY = distance.toFloat()
//            }
//        })
//        sectionAdapter.addSection(AllFormSection(editClick, "Test A", arrayListOf()))
//        sectionAdapter.addSection(ActiveFormSection(editClick, deleteClick, shareClick, "Test A", arrayListOf()))
        presenter.submitLoadAllForms()
    }

    private fun onEditFormClicked(form: Form) {
        router.pushController(RouterTransaction.with(FormController(form)))
    }

    private fun onDeleteFormClicked(form: Form) {
        presenter.submitDeleteForm(form)
//        sectionAdapter.noire
    }

    private fun onShareFormClicked(form: Form) {
        router.pushController(RouterTransaction.with(FormController(form)))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.host_form_view, container, false)
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
    }

    override fun showModelAndActiveForms(modelForms: List<Form>, activeForms: List<Form>) {
        sectionAdapter.removeAllSections()
        sectionAdapter.notifyDataSetChanged()
        allFormRecycleView?.let {
            it.adapter = sectionAdapter
            sectionAdapter.addSection(AllFormSection(editClick, "Test A", modelForms))
            sectionAdapter.addSection(ActiveFormSection(editClick, deleteClick, shareClick, "Test B", activeForms))

        }
    }

    override fun getTitleToolbar() = applicationContext?.getString(R.string.form_title)!!

    override fun getEnableBackAction() = false

}