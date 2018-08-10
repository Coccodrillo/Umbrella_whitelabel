package org.secfirst.umbrella.whitelabel.feature.form.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection
import kotlinx.android.synthetic.main.active_form_item_view.view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.data.Form
import org.secfirst.umbrella.whitelabel.misc.HeaderViewHolder


class ActiveFormSection(private val onEditItemClick: (Form) -> Unit,
                        private val onDeleteItemClick: (Form) -> Unit,
                        private val onShareItemClick: (Form) -> Unit,
                        private val titleSection: String,
                        private val forms: List<Form>) : StatelessSection(SectionParameters.builder()
        .itemResourceId(R.layout.active_form_item_view)
        .headerResourceId(R.layout.section)
        .build()) {

    override fun getContentItemsTotal() = forms.size

    override fun onBindItemViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val activeFormHolder = holder as ItemActiveFormHolder
        val currentTime = forms[position].date
        val title = forms[position].title
        activeFormHolder.bind(title, currentTime, editClickListener = { onEditItemClick(forms[position]) },
                shareClickListener = { onShareItemClick(forms[position]) },
                deleteClickListener = { onDeleteItemClick(forms[position]) })

    }

    override fun getHeaderViewHolder(view: View): RecyclerView.ViewHolder {
        return HeaderViewHolder(view)
    }

    override fun onBindHeaderViewHolder(holder: RecyclerView.ViewHolder?) {
        super.onBindHeaderViewHolder(holder)
        val headerHolder = holder as HeaderViewHolder?
        headerHolder?.let { it.sectionText.text = titleSection }
    }

    override fun getItemViewHolder(view: View?) = ItemActiveFormHolder(view)

}


class ItemActiveFormHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {

    private val titleForm = itemView?.titleActiveForm
    private val currentTime = itemView?.currentTime
    private val edit = itemView?.editForm
    private val share = itemView?.shareForm
    private val delete = itemView?.deleteForm

    fun bind(title: String, timeNow: String,
             editClickListener: (ItemActiveFormHolder) -> Unit,
             deleteClickListener: (ItemActiveFormHolder) -> Unit,
             shareClickListener: (ItemActiveFormHolder) -> Unit) {

        titleForm?.let { it.text = title }
        currentTime?.let { it.text = timeNow }
        edit?.let { it -> it.setOnClickListener { editClickListener(this) } }
        share?.let { it -> it.setOnClickListener { shareClickListener(this) } }
        delete?.let { it -> it.setOnClickListener { deleteClickListener(this) } }
    }
}

