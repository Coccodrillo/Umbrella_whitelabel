package org.secfirst.umbrella.whitelabel.feature.reader.rss.view

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.support.RouterPagerAdapter
import org.secfirst.umbrella.whitelabel.feature.reader.rss.view.RssController

class FeedAdapter(host: Controller) : RouterPagerAdapter(host) {
    override fun configureRouter(router: Router, position: Int) {
        if (!router.hasRootController()) {
            when (position) {
                1 -> router.setRoot(RouterTransaction.with(RssController()))
                2 -> router.setRoot(RouterTransaction.with(RssController()))
            }
        }
    }

    override fun getPageTitle(position: Int) = if (position == 0) "FEED" else "RSS"

    override fun getCount() = 2
}