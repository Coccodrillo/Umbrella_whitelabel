package org.secfirst.umbrella.ui.standard.interactor

import android.content.Context
import io.reactivex.Observable
import org.secfirst.umbrella.data.database.standard.Standard
import org.secfirst.umbrella.data.internal.Category
import org.secfirst.umbrella.data.network.BlogResponse
import org.secfirst.umbrella.ui.base.interactor.BaseInteractor
import java.util.*

interface StandardBaseInteractor : BaseInteractor {

    fun getBlogList(): Observable<BlogResponse>

    fun submitQuestion(standard: Standard): Long

    fun getData(): List<Standard>

    fun getTentCategory(context : Context): List<Category>?
}
