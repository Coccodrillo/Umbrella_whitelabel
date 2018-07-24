package org.secfirst.umbrella.feature.form.view


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.view.Gravity
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import com.stepstone.stepper.Step
import com.stepstone.stepper.VerificationError
import org.jetbrains.anko.*
import org.secfirst.umbrella.R
import org.secfirst.umbrella.data.Item
import org.secfirst.umbrella.data.Option
import org.secfirst.umbrella.data.Screen
import org.secfirst.umbrella.feature.form.FieldType
import org.secfirst.umbrella.feature.form.view.controller.FormInputController


class FormUI(private val screen: Screen) : AnkoComponent<FormInputController>, Step {

    override fun createView(ui: AnkoContext<FormInputController>) = ui.apply {
        val size = 16f

        scrollView {
            background = ColorDrawable(Color.parseColor("#f6f6f6"))
            verticalLayout {
                padding = dip(20)
                screen.items.forEach { item ->
                    when (item.type) {

                        FieldType.LABEL.value -> textView(item.label) {
                            textSize = 18f
                            padding = dip(10)
                            textColor = ContextCompat.getColor(context, R.color.umbrella_purple)
                        }.lparams { gravity = Gravity.CENTER }

                        FieldType.TEXT_AREA.value -> {
                            textView(item.label) { textSize = size }.lparams { topMargin = dip(10) }
                            val editText = editText {
                                hint = item.hint
                            }.lparams(width = matchParent)
                            val editTextMap = hashMapOf<EditText, Item>()
                            editTextMap[editText] = item
                            ui.owner.editTextList.add(editTextMap)
                        }
                        FieldType.TEXT_INPUT.value -> {
                            textView(item.label) { textSize = size }.lparams { topMargin = dip(10) }
                            val editText = editText {
                                hint = item.hint
                            }.lparams(width = matchParent)
                            val editTextMap = hashMapOf<EditText, Item>()
                            editTextMap[editText] = item
                            ui.owner.editTextList.add(editTextMap)
                        }

                        FieldType.MULTIPLE_CHOICE.value -> {
                            textView(item.label) { textSize = size }.lparams { topMargin = dip(10) }
                            item.options.forEach { formOption ->
                                val checkBox = checkBox(formOption.label)
                                val checkboxMap = hashMapOf<CheckBox, Option>()
                                checkboxMap[checkBox] = formOption
                                ui.owner.checkboxList.add(checkboxMap)
                            }
                        }
                        FieldType.SINGLE_CHOICE.value -> {
                            textView(item.label)
                            item.options.forEach { formOption ->
                                val radioButton = radioButton {
                                    text = formOption.label
                                }
                                val radioButtonMap = hashMapOf<RadioButton, Option>()
                                radioButtonMap[radioButton] = formOption
                                ui.owner.radioButtonList.add(radioButtonMap)
                            }
                        }
                    }
                }

            }.lparams(width = matchParent, height = matchParent)
        }

    }.view

    override fun onSelected() {}

    override fun verifyStep(): Nothing? = null

    override fun onError(error: VerificationError) {}

}