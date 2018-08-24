package org.secfirst.umbrella.whitelabel.data.database.rss

interface RssRepo {

    suspend fun saveFeed(RSS: RSS)
    suspend fun getAllFeeds() : List<RSS>
}