package org.secfirst.umbrella.data.local.standard

import io.reactivex.Observable
import io.reactivex.Single

interface StandardRepo {

    fun insertQuestions(questions: List<Standard>): Observable<Boolean>

    fun loadQuestions(): Single<List<Standard>>
}