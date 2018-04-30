package org.secfirst.umbrella.data.local.standard

import io.reactivex.Single

interface StandardRepo {

    fun insertStandard(standard: Standard): Long

    fun loadQuestions(): Single<List<Standard>>

    fun getStandard(): List<Standard>
}
