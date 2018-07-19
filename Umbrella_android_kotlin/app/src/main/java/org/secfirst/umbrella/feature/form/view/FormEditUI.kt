package org.secfirst.umbrella.feature.form.view


import android.graphics.Color
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import org.jetbrains.anko.*
import org.secfirst.umbrella.feature.base.view.BaseController


class FormEditUI : AnkoComponent<BaseController>, Step {

    override fun createView(ui: AnkoContext<BaseController>) = ui.apply {
        verticalLayout {
            linearLayout {
                button("dodô")
                textView("opa")
                backgroundColor = Color.RED

            }.lparams(width = matchParent, height = matchParent)
        }
    }.view

    override fun onSelected() {}

    override fun verifyStep() = VerificationError("Click more times!")

    override fun onError(error: VerificationError) {}
}