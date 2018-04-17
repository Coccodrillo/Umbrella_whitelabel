package org.secfirst.umbrella.ui.standard.interactor

import io.reactivex.Observable
import org.secfirst.umbrella.data.network.BlogResponse
import org.secfirst.umbrella.ui.base.interactor.MVPInteractor

interface StandardMVPInteractor : MVPInteractor {

    fun getBlogList(): Observable<BlogResponse>
}