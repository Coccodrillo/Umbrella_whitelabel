package org.secfirst.umbrella.whitelabel.feature.feed.adapter

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.support.RouterPagerAdapter
import org.secfirst.umbrella.whitelabel.feature.feed.view.FeedController

class FeedAdapter(host: Controller) : RouterPagerAdapter(host) {
    override fun configureRouter(router: Router, position: Int) {
        if (!router.hasRootController()) {
            when (position) {
                1 -> router.setRoot(RouterTransaction.with(FeedController()))
                2 -> router.setRoot(RouterTransaction.with(FeedController()))
            }
        }
    }

    override fun getPageTitle(position: Int) = if (position == 0) "FEED" else "RSS"

    override fun getCount() = 2
}