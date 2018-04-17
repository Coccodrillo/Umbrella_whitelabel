package org.secfirst.umbrella.data.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import org.secfirst.umbrella.di.ApiKeyInfo
import javax.inject.Inject

/**
 * Just define possible header for requests.
 */
class ApiHeader @Inject constructor(internal val publicApiHeader: PublicApiHeader) {

    class PublicApiHeader @Inject constructor(@ApiKeyInfo
                                              @Expose
                                              @SerializedName
                                              ("api_key") val apiKey: String)
}

/**
 * Make all requisitions tracked by @AppApiHelper
 */
class AppApiHelper @Inject constructor(private val apiHeader: ApiHeader) : ApiHelper {

    override fun getBlogApiCall(): Observable<BlogResponse> =
            Rx2AndroidNetworking.get(NetworkEndPoint.ENDPOINT_BLOG)
                    .addHeaders(apiHeader.publicApiHeader)
                    .build()
                    .getObjectObservable(BlogResponse::class.java)


}

/**
 * Responsible to tracking all API calls.
 */
interface ApiHelper {

    fun getBlogApiCall(): Observable<BlogResponse>
}