package org.secfirst.umbrella.data.database.standard

import io.reactivex.Single
import javax.inject.Inject

class StandardRepository @Inject internal constructor(private val standardDao: StandardDao) : StandardRepo {


    override fun insertStandard(standard: Standard) = standardDao.insert(standard)

    override fun getStandard() = standardDao.getStandard()


    override fun loadQuestions(): Single<List<Standard>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
