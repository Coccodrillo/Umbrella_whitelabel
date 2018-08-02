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
import kotlinx.android.synthetic.main.form_view.*
import org.secfirst.umbrella.R
import org.secfirst.umbrella.UmbrellaApplication
import org.secfirst.umbrella.data.Answer
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Item
import org.secfirst.umbrella.data.Option
import org.secfirst.umbrella.feature.MainActivity
import org.secfirst.umbrella.feature.base.view.BaseController
import org.secfirst.umbrella.feature.form.DaggerFormComponent
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.presenter.FormBasePresenter
import org.secfirst.umbrella.feature.form.view.FormUI
import org.secfirst.umbrella.feature.form.view.FormView
import org.secfirst.umbrella.feature.form.view.adapter.FormAdapter
import org.secfirst.umbrella.feature.main.OnNavigationBottomView
import org.secfirst.umbrella.misc.BundleExt.Companion.EXTRA_FORM_SELECTED
import org.secfirst.umbrella.misc.currentTime
import org.secfirst.umbrella.misc.hideKeyboard
import javax.inject.Inject

class FormController(bundle: Bundle) : BaseController(bundle), FormView, StepperLayout.StepperListener {

    @Inject
    internal lateinit var presenter: FormBasePresenter<FormView, FormBaseInteractor>
    var editTextList = mutableListOf<HashMap<EditText, Item>>()
    var radioButtonList = mutableListOf<HashMap<RadioButton, Option>>()
    var checkboxList = mutableListOf<HashMap<CheckBox, Option>>()
    private lateinit var onNavigation: OnNavigationBottomView
    private var listOfViews: MutableList<FormUI> = mutableListOf()
    private var formStartTime = ""
    private lateinit var newForm: Form


    constructor(formSelected: Form) : this(Bundle().apply {
        putSerializable(EXTRA_FORM_SELECTED, formSelected)
    })

    private val formSelected by lazy { args.getSerializable(EXTRA_FORM_SELECTED) as Form }


    override fun onAttach(view: View) {
        super.onAttach(view)
        createFormUI()
        stepperLayout.adapter = FormAdapter(formSelected, this, listOfViews)
        stepperLayout.setListener(this)
        onNavigation = activity as MainActivity
        onNavigation.hideBottomMenu()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.form_view, container, false)
        createFormUI()
        createActiveForm()
        return view
    }

    override fun onDestroy() {
        onNavigation.showBottomMenu()
        super.onDestroy()
    }

    private fun createActiveForm(){
        newForm = formSelected
        newForm.referenceId = formSelected.id
        newForm.id = System.currentTimeMillis()
        newForm.active = true
    }
    private fun createFormUI() {
        for (view in formSelected.screens)
            listOfViews.add(FormUI(view, formSelected.answers))
    }

    override fun onCompleted(completeButton: View?) {
        formStartTime = currentTime
        bindCheckboxValue()
        bindEditTextValue()
        bindRadioButtonValue()
        hideKeyboard()
        router.popCurrentController()
    }

    override fun onInject() {
        DaggerFormComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }


    private fun bindCheckboxValue() {
        checkboxList.forEach { map ->
            val answer = Answer()
            for (entry in map) {
                val formOption = entry.value
                val checkbox = entry.key
                answer.choiceInput = checkbox.isChecked
                answer.form = formSelected
                answer.option = formOption
                newForm.date = formStartTime

                if (answer.choiceInput)
                    newForm.answers.add(answer)

            }

            presenter.submitForm(newForm)
        }
    }

    private fun bindEditTextValue() {
        editTextList.forEach { map ->
            val answer = Answer()
            for (entry in map) {
                val item = entry.value
                val editText = entry.key
                answer.textInput = editText.text.toString()
                answer.form = formSelected
                answer.item = item
                newForm.date = formStartTime
                if (answer.textInput.isNotEmpty())
                    newForm.answers.add(answer)

            }
            presenter.submitForm(newForm)
        }
    }

    private fun bindRadioButtonValue() {
        radioButtonList.forEach { map ->
            val answer = Answer()
            for (entry in map) {
                val formOption = entry.value
                val radioButton = entry.key
                answer.choiceInput = radioButton.isChecked
                answer.form = formSelected
                answer.option = formOption
                newForm.date = formStartTime
                if (answer.choiceInput)
                    newForm.answers.add(answer)

            }
            presenter.submitForm(newForm)
        }
    }

    override fun onStepSelected(newStepPosition: Int) {}

    override fun onError(verificationError: VerificationError?) {}

    override fun onReturn() {}

    override fun getTitleToolbar() = formSelected.title

    override fun getEnableBackAction() = true

}