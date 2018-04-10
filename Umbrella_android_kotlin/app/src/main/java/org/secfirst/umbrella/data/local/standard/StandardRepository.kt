package org.secfirst.umbrella.data.local.standard

import io.reactivex.Observable
import javax.inject.Inject

class StandardRepository @Inject internal constructor(private val standardDao: StandardDao) : StandardRepo {

    override fun insertQuestions(questions: List<Standard>): Observable<Boolean> {
        standardDao.insertAll(questions)
        return Observable.just(true)
    }

    override fun loadQuestions(): Observable<List<Standard>> = Observable.fromCallable({ standardDao.loadAll() })
}