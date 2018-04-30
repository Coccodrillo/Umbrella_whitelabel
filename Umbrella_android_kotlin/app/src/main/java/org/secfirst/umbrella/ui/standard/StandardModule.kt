package org.secfirst.umbrella.ui.standard

import dagger.Module
import dagger.Provides
import org.secfirst.umbrella.ui.standard.interactor.StandardInteractorImp
import org.secfirst.umbrella.ui.standard.interactor.StandardBaseInteractor
import org.secfirst.umbrella.ui.standard.presenter.StandardBasePresenter
import org.secfirst.umbrella.ui.standard.presenter.StandardPresenterImp
import org.secfirst.umbrella.ui.standard.view.StandardBaseView

@Module
class StandardModule {

    @Provides
    internal fun provideBlogInteractor(interactor: StandardInteractorImp): StandardBaseInteractor = interactor

    @Provides
    internal fun provideBlogPresenter(presenter: StandardPresenterImp<StandardBaseView, StandardBaseInteractor>)
            : StandardBasePresenter<StandardBaseView, StandardBaseInteractor> = presenter

}


