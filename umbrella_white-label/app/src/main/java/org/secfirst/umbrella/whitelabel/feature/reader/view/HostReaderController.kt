package org.secfirst.umbrella.whitelabel.feature.reader.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.host_reader_view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController


class HostReaderController : BaseController() {


    override fun onInject() {
    }

    override fun onAttach(view: View) {
        feedPager?.adapter = ReaderAdapter(this)
        feedTab?.setupWithViewPager(feedPager)
        disableToolbar()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.host_reader_view, container, false)
    }

    override fun onDestroyView(view: View) {
        feedTab.setupWithViewPager(null)
        super.onDestroyView(view)
    }

    override fun getTitleToolbar() = ""

    override fun getEnableBackAction() = false

}

