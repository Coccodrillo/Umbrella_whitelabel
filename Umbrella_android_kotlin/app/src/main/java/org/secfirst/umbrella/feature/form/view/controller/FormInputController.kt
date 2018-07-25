package org.secfirst.umbrella.feature.form.view.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import com.stepstone.stepper.StepperLayout
import com.stepstone.stepper.VerificationError
import kotlinx.android.synthetic.main.form_edit_view.*
import org.secfirst.umbrella.R
import org.secfirst.umbrella.UmbrellaApplication
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Item
import org.secfirst.umbrella.data.Option
import org.secfirst.umbrella.data.Value
import org.secfirst.umbrella.feature.MainActivity
import org.secfirst.umbrella.feature.base.view.BaseController
import org.secfirst.umbrella.feature.form.DaggerFormComponent
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.presenter.FormBasePresenter
import org.secfirst.umbrella.feature.form.view.FormUI
import org.secfirst.umbrella.feature.form.view.FormView
import org.secfirst.umbrella.feature.form.view.adapter.FormInputAdapter
import org.secfirst.umbrella.feature.main.OnNavigationBottomView
import org.secfirst.umbrella.util.BundleExt.Companion.EXTRA_FORM_SELECTED
import org.secfirst.umbrella.util.currenttime
import javax.inject.Inject

class FormInputController(bundle: Bundle) : BaseController(bundle), FormView, StepperLayout.StepperListener {


    @Inject
    internal lateinit var presenter: FormBasePresenter<FormView, FormBaseInteractor>
    var editTextList = mutableListOf<HashMap<EditText, Item>>()
    var radioButtonList = mutableListOf<HashMap<RadioButton, Option>>()
    var checkboxList = mutableListOf<HashMap<CheckBox, Option>>()
    private lateinit var onNavigation: OnNavigationBottomView
    private var listOfViews: MutableList<FormUI> = mutableListOf()


    constructor(formSelected: Form) : this(Bundle().apply {
        putSerializable(EXTRA_FORM_SELECTED, formSelected)
    })

    private val formSelected by lazy { args.getSerializable(EXTRA_FORM_SELECTED) as Form }


    override fun onAttach(view: View) {
        super.onAttach(view)
        stepperLayout.adapter = FormInputAdapter(formSelected, this, listOfViews)
        stepperLayout.setListener(this)
        onNavigation = activity as MainActivity
        onNavigation.hiddenBottomMenu()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.form_edit_view, container, false)
        createFormUI()
        return view
    }

    override fun onDestroy() {
        onNavigation.showBottomMenu()
        super.onDestroy()
    }

    private fun createFormUI() {
        for (view in formSelected.screens)
            listOfViews.add(FormUI(view))
    }

    override fun onInject() {
        DaggerFormComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }


    override fun onCompleted(completeButton: View?) {
        bindCheckboxValue()
        bindEditTextValue()
        bindRadioButtonValue()
    }


    private fun bindCheckboxValue() {
        checkboxList.forEach { map ->
            val value = Value()
            for (entry in map) {
                val formOption = entry.value
                val checkbox = entry.key
                value.choiceInput = checkbox.isChecked
                value.form = formSelected
                value.option = formOption
                value.dataTime = currenttime
                value.id = 0
                if (value.choiceInput) presenter.submitInsert(value)
            }
        }
    }

    private fun bindEditTextValue() {
        editTextList.forEach { map ->
            val value = Value()
            for (entry in map) {
                val item = entry.value
                val editText = entry.key
                value.textInput = editText.text.toString()
                value.form = formSelected
                value.item = item
                value.dataTime = currenttime
                value.id = 0
                if (value.textInput.isNotEmpty()) presenter.submitInsert(value)
            }
        }
    }

    private fun bindRadioButtonValue() {
        radioButtonList.forEach { map ->
            val value = Value()
            for (entry in map) {
                val formOption = entry.value
                val radioButton = entry.key
                value.choiceInput = radioButton.isChecked
                value.form = formSelected
                value.option = formOption
                value.dataTime = currenttime
                value.id = 0
                if (value.choiceInput) presenter.submitInsert(value)
            }
        }
    }

    override fun onStepSelected(newStepPosition: Int) {}

    override fun onError(verificationError: VerificationError?) {}

    override fun onReturn() {}
}