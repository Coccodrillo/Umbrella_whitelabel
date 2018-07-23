package org.secfirst.umbrella.feature.form.view

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
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.feature.MainActivity
import org.secfirst.umbrella.feature.base.view.BaseController
import org.secfirst.umbrella.feature.form.view.adapter.FormInputAdapter
import org.secfirst.umbrella.util.BundleExt.Companion.EXTRA_FORM_SELECTED

class FormInputController(bundle: Bundle) : BaseController(bundle), StepperLayout.StepperListener {

    lateinit var radionButton: RadioButton
    lateinit var checkBox: CheckBox
    lateinit var editText: EditText
    
    constructor(formSelected: Form) : this(Bundle().apply {
        putSerializable(EXTRA_FORM_SELECTED, formSelected)
    })

    private lateinit var mainActivity: MainActivity
    private val formSelected by lazy { args.getSerializable(EXTRA_FORM_SELECTED) as Form }


    override fun onAttach(view: View) {
        super.onAttach(view)
        mainActivity.hiddenBottomMenu()
        stepperLayout.adapter = FormInputAdapter(formSelected, this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.form_edit_view, container, false)
        mainActivity = activity as MainActivity
        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivity.showBottomMenu()
    }

    override fun onInject() {}

    override fun onStepSelected(newStepPosition: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onError(verificationError: VerificationError?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onReturn() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCompleted(completeButton: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}