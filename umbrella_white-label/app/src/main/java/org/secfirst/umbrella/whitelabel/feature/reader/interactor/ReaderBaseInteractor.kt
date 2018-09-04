package org.secfirst.umbrella.whitelabel.feature.reader.interactor


import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RefRSSItem
import org.secfirst.umbrella.whitelabel.feature.base.interactor.BaseInteractor

interface ReaderBaseInteractor : BaseInteractor {

    suspend fun insertRss(refRSS: RefRSSItem)

    suspend fun insertAllRss(rssList: List<RefRSSItem>)

    suspend fun fetchRss(): List<RefRSSItem>

    suspend fun deleteRss(refRSS: RefRSSItem)

    suspend fun doRSsCall(url: String): Deferred<ResponseBody>

}