package org.secfirst.umbrella.whitelabel.feature.reader.view

import com.einmalfel.earl.Feed
import org.secfirst.umbrella.whitelabel.feature.base.view.BaseView

interface ReaderView : BaseView {

    fun showAllRss(rss: List<Feed>)

    fun showNewestRss(rss: Feed)
}