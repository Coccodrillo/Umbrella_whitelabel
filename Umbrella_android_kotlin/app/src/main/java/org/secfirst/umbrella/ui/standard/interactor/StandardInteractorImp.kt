package org.secfirst.umbrella.ui.standard.interactor


import android.util.Log
import org.secfirst.umbrella.data.local.standard.Standard
import org.secfirst.umbrella.data.local.standard.StandardRepo
import org.secfirst.umbrella.data.network.ApiHelper
import org.secfirst.umbrella.ui.base.interactor.BaseInteractorImp
import javax.inject.Inject

class StandardInteractorImp @Inject constructor(apiHelper: ApiHelper, val standardRepo: StandardRepo) : BaseInteractorImp(apiHelper), StandardBaseInteractor {

    override fun getAuthorListInDatabase(standards: List<Standard>) {
        standardRepo.insertQuestions(standards)
        Log.i("test", "passei no interactor")
    }

    override fun getBlogList() = apiHelper.getBlogApiCall()
}