package org.secfirst.umbrella.whitelabel.feature.feed.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.host_feed_view.*
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController
import org.secfirst.umbrella.whitelabel.feature.feed.adapter.FeedAdapter


class HostFeedController : BaseController() {


    override fun onInject() {
    }

    override fun onAttach(view: View) {
        feedPager?.adapter = FeedAdapter(this)
        feedTab?.setupWithViewPager(feedPager)
        disableToolbar()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        return inflater.inflate(R.layout.host_feed_view, container, false)
    }

    override fun onDestroyView(view: View) {
        feedTab.setupWithViewPager(null)
        super.onDestroyView(view)
    }

    override fun getTitleToolbar() = ""

    override fun getEnableBackAction() = false

}

