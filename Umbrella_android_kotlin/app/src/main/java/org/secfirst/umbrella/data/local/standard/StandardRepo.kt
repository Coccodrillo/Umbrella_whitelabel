package org.secfirst.umbrella.data.local.standard

import io.reactivex.Observable

interface StandardRepo {

    fun insertQuestions(questions: List<Standard>): Observable<Boolean>

}