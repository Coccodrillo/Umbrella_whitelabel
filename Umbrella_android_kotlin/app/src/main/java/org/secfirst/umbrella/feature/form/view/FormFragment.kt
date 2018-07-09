package org.secfirst.umbrella.feature.form.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.secfirst.umbrella.R
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.feature.base.view.BaseFragment
import org.secfirst.umbrella.feature.form.interactor.FormBaseInteractor
import org.secfirst.umbrella.feature.form.presenter.FormBasePresenter
import javax.inject.Inject

class FormFragment : BaseFragment(), FormBaseView {

    @Inject
    internal lateinit var presenter: FormBasePresenter<FormBaseView, FormBaseInteractor>

    companion object {
        fun newInstance(): FormFragment = FormFragment()
    }

    override fun setUp() {
        presenter.loadForms()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_standard, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter.onAttach(this)
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun showForms(forms: List<Form>) {
        forms.forEach { Log.e("test", "forms -  $it") }
    }
}