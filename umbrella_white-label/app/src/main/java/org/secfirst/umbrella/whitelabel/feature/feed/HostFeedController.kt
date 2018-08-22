package org.secfirst.umbrella.whitelabel.feature.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseController


class HostFeedController : BaseController() {


    override fun onInject() {
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.feed_view, container, false)
        return view
    }
    override fun getTitleToolbar() = ""

    override fun getEnableToolbar() = false

    override fun getEnableBackAction() = false

}

