package org.secfirst.umbrella.whitelabel.data.database.reader.rss

import javax.inject.Inject

class RssRepository @Inject constructor(private val rssDao: RssDao) : RssRepo {

    override suspend fun delete(rss: RSS) = rssDao.delete(rss)

    override suspend fun saveFeed(rss: RSS) = rssDao.save(rss)

    override suspend fun getAllFeeds() = rssDao.getAll()
}