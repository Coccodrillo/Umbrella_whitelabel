package org.secfirst.umbrella.ui.standard.interactor

import io.reactivex.Observable
import org.secfirst.umbrella.data.local.standard.Standard
import org.secfirst.umbrella.ui.base.interactor.MVPInteractor

interface StandardMVPInteractor : MVPInteractor {
    fun seedQuestions(): Observable<Boolean>
    fun getQuestion(): Observable<List<Standard>>
}