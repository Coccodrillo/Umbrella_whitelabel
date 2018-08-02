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
import org.secfirst.umbrella.data.Answer
import org.secfirst.umbrella.data.Item
import org.secfirst.umbrella.data.Option
import org.secfirst.umbrella.data.Screen
import org.secfirst.umbrella.feature.form.FieldType
import org.secfirst.umbrella.feature.form.view.controller.FormController


class FormUI(private val screen: Screen, private val answers: List<Answer>?) : AnkoComponent<FormController>, Step {

    override fun createView(ui: AnkoContext<FormController>) = ui.apply {
        val size = 16f

        scrollView {
            background = ColorDrawable(Color.parseColor("#f6f6f6"))
            verticalLayout {
                padding = dip(20)
                screen.items.forEach { item ->
                    when (item.type) {

                        FieldType.LABEL.value ->

                            textView(item.label) {
                                textSize = 18f
                                padding = dip(10)
                                textColor = ContextCompat.getColor(context, R.color.umbrella_purple)
                            }.lparams { gravity = Gravity.CENTER }
                        FieldType.TEXT_AREA.value -> {

                            textView(item.label) { textSize = size }.lparams { topMargin = dip(10) }
                            val editText = editText {
                                hint = item.hint
                                setText(isData(item))
                            }.lparams(width = matchParent)
                            bindEditText(item, editText, ui)
                        }
                        FieldType.TEXT_INPUT.value -> {

                            textView(item.label) { textSize = size }.lparams { topMargin = dip(10) }
                            val editText = editText {
                                hint = item.hint
                                setText(isData(item))
                            }.lparams(width = matchParent)
                            bindEditText(item, editText, ui)
                        }
                        FieldType.MULTIPLE_CHOICE.value -> {

                            textView(item.label) { textSize = size }.lparams { topMargin = dip(10) }
                            item.options.forEach { formOption ->
                                val checkBox = checkBox {
                                    text = formOption.label
                                    isChecked = isData(formOption)
                                }
                                bindCheckBox(formOption, checkBox, ui)
                            }
                        }
                        FieldType.SINGLE_CHOICE.value -> {

                            textView(item.label)
                            item.options.forEach { formOption ->
                                val radioButton = radioButton {
                                    text = formOption.label
                                    isChecked = isData(formOption)
                                }
                                bindRadioButton(formOption, radioButton, ui)
                            }
                        }
                    }
                }

            }.lparams(width = matchParent, height = matchParent)
        }

    }.view

    private fun isData(formOption: Option): Boolean {
        answers?.forEach { answer ->
            if (formOption.id == answer.option?.id)
                return true
        }
        return false
    }

    private fun isData(item: Item): String {
        answers?.forEach { answer ->
            if (item.id == answer.item?.id) {
                return answer.textInput
            }
        }
        return ""
    }

    private fun bindRadioButton(formOption: Option, radioButton: RadioButton, ui: AnkoContext<FormController>) {
        val radioButtonMap = hashMapOf<RadioButton, Option>()
        radioButtonMap[radioButton] = formOption
        ui.owner.radioButtonList.add(radioButtonMap)
    }

    private fun bindCheckBox(formOption: Option, checkBox: CheckBox, ui: AnkoContext<FormController>) {
        val checkboxMap = hashMapOf<CheckBox, Option>()
        checkboxMap[checkBox] = formOption
        ui.owner.checkboxList.add(checkboxMap)
    }

    private fun bindEditText(item: Item, editText: EditText, ui: AnkoContext<FormController>) {
        val editTextMap = hashMapOf<EditText, Item>()
        editTextMap[editText] = item
        ui.owner.editTextList.add(editTextMap)
    }

    override fun onSelected() {}

    override fun verifyStep(): Nothing? = null

    override fun onError(error: VerificationError) {}
}