package org.secfirst.umbrella.whitelabel.data.database.rss

import javax.inject.Inject

class RssRepository @Inject constructor(private val RssDao: RssDao) : RssRepo {

    override suspend fun saveFeed(RSS: RSS) = RssDao.save(RSS)

    override suspend fun getAllFeeds() = RssDao.getAll()
}