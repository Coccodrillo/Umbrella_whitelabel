package org.secfirst.umbrella.feature.form.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import kotlinx.android.synthetic.main.active_form_item_view.view.*
import org.secfirst.umbrella.R
import org.secfirst.umbrella.UmbrellaApplication
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.feature.form.view.controller.FormInputController


class ActiveFormAdapter(private val formList: List<Form>,
                        private val router: Router) : RecyclerView.Adapter<ActiveFormViewHolder>() {

    private val context = UmbrellaApplication.instance

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveFormViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.active_form_item_view, parent, false)
        return ActiveFormViewHolder(view)
    }

    override fun getItemCount() = formList.size

    override fun onBindViewHolder(formHolder: ActiveFormViewHolder, position: Int) {

        val currentTime = formList[position].data!!.last().dataTime
        val title = formList[position].title
        formHolder.bind(title, currentTime, clickListener = {
            router.pushController(RouterTransaction.with(FormInputController(formList[it.adapterPosition])))
        })
    }

}

class ActiveFormViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val titleForm = itemView.titleActiveForm
    private val currentTime = itemView.currentTime
    private val edit = itemView.editForm
    private val share = itemView.shareForm
    private val delete = itemView.deleteForm

    fun bind(title: String, timeNow: String, clickListener: (ActiveFormViewHolder) -> Unit) {
        titleForm.text = title
        currentTime.text = timeNow
        itemView.setOnClickListener { clickListener(this) }
    }
}