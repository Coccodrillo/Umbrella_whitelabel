package org.secfirst.umbrella.ui.standard.interactor

import io.reactivex.Observable
import org.secfirst.umbrella.data.local.standard.Standard
import org.secfirst.umbrella.data.network.BlogResponse
import org.secfirst.umbrella.ui.base.interactor.BaseInteractor

interface StandardBaseInteractor : BaseInteractor {

    fun getBlogList(): Observable<BlogResponse>

    fun submitQuestion(standard: Standard): Long

    fun getData(): List<Standard>
}
