package org.secfirst.umbrella.whitelabel.feature.reader.rss.interactor

import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RSS
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RssRepo
import org.secfirst.umbrella.whitelabel.feature.base.interactor.BaseInteractorImp
import javax.inject.Inject

class RssInteractorImp @Inject constructor(private val rssRepo: RssRepo) : BaseInteractorImp(), RssBaseInteractor {

    override suspend fun deleteRss(rss: RSS) = rssRepo.delete(rss)

    override suspend fun insertRss(rss: RSS) = rssRepo.saveFeed(rss)

    override suspend fun fetchRss(): List<RSS> = rssRepo.getAllFeeds()
}