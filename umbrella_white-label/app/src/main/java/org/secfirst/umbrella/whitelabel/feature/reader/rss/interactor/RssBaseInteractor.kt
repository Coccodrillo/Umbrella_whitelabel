package org.secfirst.umbrella.whitelabel.feature.reader.rss.interactor


import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RSS
import org.secfirst.umbrella.whitelabel.feature.base.interactor.BaseInteractor

interface RssBaseInteractor : BaseInteractor {

    suspend fun insertRss(rss: RSS)

    suspend fun fetchRss(): List<RSS>

    suspend fun deleteRss(rss: RSS): Boolean

}