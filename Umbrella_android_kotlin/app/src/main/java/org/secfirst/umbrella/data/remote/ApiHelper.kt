package org.secfirst.umbrella.data.remote

import io.reactivex.Observable

/**
 * Created by jyotidubey on 04/01/18.
 */
interface  ApiHelper {

    fun getBlogApiCall(): Observable<BlogResponse>
}