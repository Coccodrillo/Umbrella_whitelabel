package org.secfirst.umbrella.whitelabel.feature.reader.rss.interactor

import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RSS
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RssRepo
import org.secfirst.umbrella.whitelabel.data.network.ApiHelper
import org.secfirst.umbrella.whitelabel.data.network.RssResponse
import org.secfirst.umbrella.whitelabel.feature.base.interactor.BaseInteractorImp
import javax.inject.Inject

class RssInteractorImp @Inject constructor(apiHelper: ApiHelper, private val rssRepo: RssRepo)
    : BaseInteractorImp(apiHelper), RssBaseInteractor {

    override suspend fun getAllRss(url: String): Deferred<ResponseBody> = apiHelper.getRss(url)

    override suspend fun deleteRss(rss: RSS) = rssRepo.delete(rss)

    override suspend fun insertRss(rss: RSS) = rssRepo.saveFeed(rss)

    override suspend fun fetchRss(): List<RSS> = rssRepo.getAllFeeds()
}