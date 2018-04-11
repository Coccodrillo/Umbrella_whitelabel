package org.secfirst.umbrella.ui.standard

import android.support.v7.widget.LinearLayoutManager
import dagger.Module
import dagger.Provides
import org.secfirst.umbrella.ui.standard.interactor.StandardInteractor
import org.secfirst.umbrella.ui.standard.interactor.StandardMVPInteractor
import org.secfirst.umbrella.ui.standard.presenter.StandardMVPPresenter
import org.secfirst.umbrella.ui.standard.presenter.StandardPresenter
import org.secfirst.umbrella.ui.standard.view.StandardFragment
import org.secfirst.umbrella.ui.standard.view.StandardMVPView
import java.util.ArrayList

@Module
class StandardModule {

    @Provides
    internal fun provideBlogInteractor(interactor: StandardInteractor): StandardMVPInteractor = interactor

    @Provides
    internal fun provideBlogPresenter(presenter: StandardPresenter<StandardMVPView, StandardMVPInteractor>)
            : StandardMVPPresenter<StandardMVPView, StandardMVPInteractor> = presenter

}

