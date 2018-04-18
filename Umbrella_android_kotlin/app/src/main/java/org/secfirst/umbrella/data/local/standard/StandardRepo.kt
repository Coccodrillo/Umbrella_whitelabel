package org.secfirst.umbrella.data.local.standard

import io.reactivex.Observable
import io.reactivex.Single

interface StandardRepo {

    fun insertStandard(standard: Standard): Observable<Boolean>

    fun loadQuestions(): Single<List<Standard>>

    fun getStandard(): Standard?
}