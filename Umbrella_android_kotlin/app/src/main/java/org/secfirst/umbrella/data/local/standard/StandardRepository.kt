package org.secfirst.umbrella.data.local.standard

import android.util.Log
import io.reactivex.Observable
import javax.inject.Inject

class StandardRepository @Inject internal constructor(private val standardDao: StandardDao) : StandardRepo {


    override fun insertQuestions(questions: List<Standard>): Observable<Boolean> {

        for (index in questions.indices) {
            standardDao.insert(questions[index])
            Log.i("test", "inseri - " + questions[index])
        }

        return Observable.just(true)
    }
}