package org.secfirst.umbrella.whitelabel.feature.reader.rss.presenter

import android.util.Log
import com.einmalfel.earl.EarlParser
import org.secfirst.umbrella.whitelabel.feature.base.presenter.BasePresenterImp
import org.secfirst.umbrella.whitelabel.feature.reader.rss.interactor.RssBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.reader.rss.view.RssView
import org.secfirst.umbrella.whitelabel.misc.AppExecutors.Companion.uiContext
import org.secfirst.umbrella.whitelabel.misc.launchSilent
import javax.inject.Inject

class RssPresenterImp<V : RssView, I : RssBaseInteractor>
@Inject internal constructor(
        interactor: I) : BasePresenterImp<V, I>(
        interactor = interactor), RssBasePresenter<V, I> {

    override fun submitFetchRss() {
        launchSilent(uiContext) {
            val test = interactor?.getAllRss("https://www.theguardian.com/world/rss")?.await()
            val a  = EarlParser.parseOrThrow(test!!.byteStream(), 0)

            Log.e("test", "rss - ${a.title}")
        }
    }
}