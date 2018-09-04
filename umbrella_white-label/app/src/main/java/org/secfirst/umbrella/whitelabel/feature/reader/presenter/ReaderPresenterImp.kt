package org.secfirst.umbrella.whitelabel.feature.reader.presenter

import android.util.Log
import com.einmalfel.earl.EarlParser
import com.einmalfel.earl.Feed
import com.google.gson.Gson
import getAssetFileBy
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RSS_FILE_NAME
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RefRSS
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RefRSSItem
import org.secfirst.umbrella.whitelabel.feature.base.presenter.BasePresenterImp
import org.secfirst.umbrella.whitelabel.feature.reader.interactor.ReaderBaseInteractor
import org.secfirst.umbrella.whitelabel.feature.reader.view.ReaderView
import org.secfirst.umbrella.whitelabel.misc.AppExecutors.Companion.uiContext
import org.secfirst.umbrella.whitelabel.misc.launchSilent
import javax.inject.Inject


class ReaderPresenterImp<V : ReaderView, I : ReaderBaseInteractor>
@Inject internal constructor(
        interactor: I) : BasePresenterImp<V, I>(
        interactor = interactor), ReaderBasePresenter<V, I> {

    override fun submitFetchRss() {
        launchSilent(uiContext) {
            var refRss: RefRSS
            val urls = mutableListOf<String>()
            interactor?.let {
                val rssRefList = it.fetchRss()
                if (rssRefList.isEmpty()) {
                    refRss = getRssFromAssert()
                    refRss.items.forEach { item -> urls.add(item.url) }
                    submitRss(refRss.items)
                } else
                    rssRefList.forEach { item -> urls.add(item.url) }
            }
            getView()?.showAllRss(processRss(urls))
        }
    }

    private fun submitRss(refRSSList: List<RefRSSItem>) {
        launchSilent(uiContext) {
            interactor?.insertAllRss(refRSSList)
        }
    }

    private suspend fun processRss(urls: List<String>): List<Feed> {
        val result = mutableListOf<Feed>()
        interactor?.let {
            urls.forEach { url ->
                try {
                    val responseBody = it.doRSsCall(url).await()
                    val feed = EarlParser.parseOrThrow(responseBody.byteStream(), 0)
                    result.add(feed)
                } catch (exception: Exception) {
                    Log.e("test", "RSS error, $url")
                }
            }
        }
        return result
    }

    private fun getRssFromAssert(): RefRSS {
        val input = getAssetFileBy(RSS_FILE_NAME)
        val jsonInString = input.bufferedReader().use { it.readText() }
        return Gson().fromJson(jsonInString, RefRSS::class.java)
    }
}