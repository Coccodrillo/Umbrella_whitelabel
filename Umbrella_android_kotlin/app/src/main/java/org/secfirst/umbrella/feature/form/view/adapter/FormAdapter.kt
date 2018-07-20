package org.secfirst.umbrella.feature.form.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.form_all_item_view.view.*
import org.secfirst.umbrella.R
import org.secfirst.umbrella.UmbrellaApplication
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.feature.form.view.FormFillController


class FormAdapter(private val forms: List<Form>, private val router: Router) : RecyclerView.Adapter<FormViewHolder>() {

    private val context = UmbrellaApplication.instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FormViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.form_all_item_view, parent, false)

        return FormViewHolder(view)
    }

    override fun getItemCount() = forms.size

    override fun onBindViewHolder(formHolder: FormViewHolder, position: Int) {
        formHolder.bind(forms[position].title, clickListener = {
            router.pushController(RouterTransaction.with(FormFillController(forms[it.adapterPosition])))
        })
    }
}

class FormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleForm = itemView.titleForm
    fun bind(title: String, clickListener: (FormViewHolder) -> Unit) {
        titleForm.text = title
        itemView.setOnClickListener { clickListener(this) }
    }
}