package org.secfirst.umbrella.data.remote

import io.reactivex.Observable

interface ApiHelper {

    fun getStandardApiCall(): Observable<SampleResponse>
}