package org.secfirst.umbrella.whitelabel.feature.reader.rss

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.einmalfel.earl.Feed
import kotlinx.android.synthetic.main.rss_view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.UmbrellaApplication
import org.secfirst.umbrella.whitelabel.component.DialogManager
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RefRSSItem
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController
import org.secfirst.umbrella.whitelabel.feature.reader.DaggerReanderComponent
import org.secfirst.umbrella.whitelabel.feature.reader.interactor.ReaderBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.reader.presenter.ReaderBasePresenter
import org.secfirst.umbrella.whitelabel.feature.reader.view.ReaderView
import javax.inject.Inject

class RssController : BaseController(), ReaderView {

    @Inject
    internal lateinit var presenter: ReaderBasePresenter<ReaderView, ReaderBaseInteractor>
    private val rssAdapter = RssAdapter()
    private lateinit var rssDialogView: View
    private lateinit var alertDialog: AlertDialog
    private lateinit var rssCancel: AppCompatTextView
    private lateinit var rssOk: AppCompatTextView
    private lateinit var rssEdit: AppCompatEditText

    override fun onInject() {
        DaggerReanderComponent.builder()
                .application(UmbrellaApplication.instance)
                .build()
                .inject(this)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        presenter.onAttach(this)
        presenter.submitFetchRss()
        rssRecycleView?.let {
            it.layoutManager = LinearLayoutManager(activity)
            it.adapter = rssAdapter
        }
        onClickRss()
        rssOk.setOnClickListener { addRss() }
        rssCancel.setOnClickListener { alertDialog.dismiss() }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        onCreateDialogView(inflater, container)
        return inflater.inflate(R.layout.rss_view, container, false)
    }

    private fun onCreateDialogView(inflater: LayoutInflater, container: ViewGroup) {
        rssDialogView = inflater.inflate(R.layout.add_rss_dialog, container, false)
        rssCancel = rssDialogView.findViewById(R.id.rssCancel)
        rssOk = rssDialogView.findViewById(R.id.rssOk)
        rssEdit = rssDialogView.findViewById(R.id.rssEditText)
        alertDialog = AlertDialog
                .Builder(activity)
                .setView(rssDialogView)
                .create()
    }


    private fun onClickRss() {
        val dialogManager = DialogManager(this)
        addRss?.let { floatButton ->
            floatButton.setOnClickListener {
                dialogManager.showDialog("TEST", object : DialogManager.DialogFactory {
                    override fun createDialog(context: Context?): Dialog {
                        return alertDialog
                    }
                })
            }
        }
    }

    private fun addRss() {
        presenter.submitInsertRss(RefRSSItem(rssEdit.text.toString()))
        alertDialog.dismiss()
    }

    override fun getEnableBackAction() = false

    override fun getTitleToolbar() = ""

    override fun showAllRss(rss: List<Feed>) = rssAdapter.addAll(rss)

    override fun showNewestRss(rss: Feed) = rssAdapter.add(rss)
}