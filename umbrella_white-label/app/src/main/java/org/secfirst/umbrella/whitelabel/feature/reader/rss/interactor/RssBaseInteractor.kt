package org.secfirst.umbrella.whitelabel.feature.reader.rss.interactor


import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RSS
import org.secfirst.umbrella.whitelabel.data.network.RssResponse
import org.secfirst.umbrella.whitelabel.feature.base.interactor.BaseInteractor

interface RssBaseInteractor : BaseInteractor {

    suspend fun insertRss(rss: RSS)

    suspend fun fetchRss(): List<RSS>

    suspend fun deleteRss(rss: RSS): Boolean

    suspend fun getAllRss(url: String): Deferred<ResponseBody>

}