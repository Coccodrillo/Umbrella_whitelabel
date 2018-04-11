package org.secfirst.umbrella.data.remote

import com.rx2androidnetworking.Rx2AndroidNetworking
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by jyotidubey on 04/01/18.
 */
class AppApiHelper @Inject constructor(private val apiHeader: ApiHeader) : ApiHelper {

    override fun getBlogApiCall(): Observable<BlogResponse> =
            Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_BLOG)
                    .addHeaders(apiHeader.publicApiHeader)
                    .build()
                    .getObjectObservable(BlogResponse::class.java)


}