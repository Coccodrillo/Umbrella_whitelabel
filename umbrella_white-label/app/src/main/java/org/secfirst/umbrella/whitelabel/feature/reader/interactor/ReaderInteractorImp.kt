package org.secfirst.umbrella.whitelabel.feature.reader.interactor

import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RefRSSItem
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RssRepo
import org.secfirst.umbrella.whitelabel.data.network.ApiHelper
import org.secfirst.umbrella.whitelabel.feature.base.interactor.BaseInteractorImp
import javax.inject.Inject

class ReaderInteractorImp @Inject constructor(apiHelper: ApiHelper, private val rssRepo: RssRepo)
    : BaseInteractorImp(apiHelper), ReaderBaseInteractor {

    override suspend fun doRSsCall(url: String): Deferred<ResponseBody> = apiHelper.getRss(url)

    override suspend fun deleteRss(refRSS: RefRSSItem) = rssRepo.delete(refRSS)

    override suspend fun insertRss(refRSS: RefRSSItem) = rssRepo.saveFeed(refRSS)

    override suspend fun fetchRss(): List<RefRSSItem> = rssRepo.getAllFeeds()
}