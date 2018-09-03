package org.secfirst.umbrella.whitelabel.data.database.reader.rss

import javax.inject.Inject

class RssRepository @Inject constructor(private val rssDao: RssDao) : RssRepo {

    override suspend fun delete(rss: RefRSSItem) = rssDao.delete(rss)

    override suspend fun saveFeed(rss: RefRSSItem) = rssDao.save(rss)

    override suspend fun getAllFeeds() = rssDao.getAll()
}