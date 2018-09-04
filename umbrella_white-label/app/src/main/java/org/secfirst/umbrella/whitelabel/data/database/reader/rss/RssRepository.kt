package org.secfirst.umbrella.whitelabel.data.database.reader.rss

import javax.inject.Inject

class RssRepository @Inject constructor(private val rssDao: RssDao) : RssRepo {

    override suspend fun saveAllRss(rssList: List<RefRSSItem>) = rssDao.saveAll(rssList)

    override suspend fun delete(rss: RefRSSItem) = rssDao.delete(rss)

    override suspend fun saveRss(rss: RefRSSItem) = rssDao.save(rss)

    override suspend fun getAllRss() = rssDao.getAll()
}