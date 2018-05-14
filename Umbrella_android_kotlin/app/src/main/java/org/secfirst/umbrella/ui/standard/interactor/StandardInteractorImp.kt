package org.secfirst.umbrella.ui.standard.interactor


import android.content.Context
import org.secfirst.umbrella.data.database.standard.Standard
import org.secfirst.umbrella.data.database.standard.StandardRepo
import org.secfirst.umbrella.data.internal.Category
import org.secfirst.umbrella.data.internal.TentRepo
import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.umbrella.ui.base.interactor.BaseInteractorImp
import java.util.*
import javax.inject.Inject
import android.os.StrictMode



class StandardInteractorImp @Inject constructor(
        private val standardRepo: StandardRepo,
        private val tentRepo: TentRepo,
        apiHelper: ApiHelper) : BaseInteractorImp(apiHelper), StandardBaseInteractor {

    override fun getTentCategory(context: Context) = tentRepo.getAllTentCategory()

    override fun submitQuestion(standard: Standard): Long {
        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        tentRepo.getAllTentCategory()
        return standardRepo.insertStandard(standard)
    }

    override fun getBlogList() = apiHelper.getBlogApiCall()

    override fun getData() = standardRepo.getStandard()
}
