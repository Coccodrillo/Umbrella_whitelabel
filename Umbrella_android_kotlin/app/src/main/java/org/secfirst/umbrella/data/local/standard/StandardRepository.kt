package org.secfirst.umbrella.data.local.standard

import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class StandardRepository @Inject internal constructor(private val standardDao: StandardDao) : StandardRepo {

    override fun loadQuestions(): Single<List<Standard>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insertQuestions(questions: List<Standard>): Observable<Boolean> {
        standardDao.insert(questions[2])
        return Observable.just(true)
    }

    //    override fun loadQuestions(): Single<List<Standard>> = standardDao.loadAll()

}