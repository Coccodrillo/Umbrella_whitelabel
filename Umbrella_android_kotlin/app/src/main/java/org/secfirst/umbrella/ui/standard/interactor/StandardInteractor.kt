package org.secfirst.umbrella.ui.standard.interactor

import android.content.Context
import io.reactivex.Observable
import org.secfirst.umbrella.data.local.standard.Standard
import org.secfirst.umbrella.data.local.standard.StandardRepo
import org.secfirst.umbrella.data.preferences.PreferenceHelper
import org.secfirst.umbrella.data.remote.ApiHelper
import org.secfirst.umbrella.ui.base.interactor.BaseInteractor
import javax.inject.Inject

class StandardInteractor
@Inject constructor(
        private val mContext: Context,
        private val questionRepoHelper: StandardRepo,
        preferenceHelper: PreferenceHelper,
        apiHelper: ApiHelper) : BaseInteractor(preferenceHelper), StandardMVPInteractor {

    override fun seedQuestions(): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getQuestion(): Observable<List<Standard>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}