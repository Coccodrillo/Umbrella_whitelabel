package org.secfirst.umbrella.feature.form.view.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.form_item.view.*
import org.secfirst.umbrella.R
import org.secfirst.umbrella.data.Form

class FormAdapter(private val forms: List<Form>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.form_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount() = forms.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleForm.text = forms[position].title
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val titleForm = view.titleForm
}