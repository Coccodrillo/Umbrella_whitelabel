package org.secfirst.umbrella.feature.form.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.form_edit_view.*
import org.secfirst.umbrella.R
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.feature.MainActivity
import org.secfirst.umbrella.feature.base.view.BaseController
import org.secfirst.umbrella.feature.form.view.adapter.FormEditAdapter
import org.secfirst.umbrella.util.BundleExt.Companion.EXTRA_FORM_SELECTED

class FormEditController(bundle: Bundle) : BaseController(bundle) {


    constructor(formSelected: Form) : this(Bundle().apply {
        putSerializable(EXTRA_FORM_SELECTED, formSelected)
    })

    private lateinit var mainActivity: MainActivity
    private val formSelected by lazy { args.getSerializable(EXTRA_FORM_SELECTED) as Form }

    override fun onInject() {

    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        mainActivity.hiddenBottomMenu()
        stepperLayout.adapter = FormEditAdapter(formSelected,this)
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
}