package org.secfirst.whitelabel.feature.tour.interactor

import org.secfirst.whitelabel.data.Root
import org.secfirst.whitelabel.feature.base.interactor.BaseInteractor

interface TourBaseInteractor : BaseInteractor {

    suspend fun fetchData(): Boolean

    suspend fun persist(root: Root)

    suspend fun initParser(): Root
}