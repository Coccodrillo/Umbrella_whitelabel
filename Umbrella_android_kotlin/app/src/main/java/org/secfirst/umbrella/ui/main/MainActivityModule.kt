package org.secfirst.umbrella.ui.main

import dagger.Module
import dagger.Provides
import org.secfirst.umbrella.ui.main.interactor.MainInteractor
import org.secfirst.umbrella.ui.main.interactor.MainMVPInteractor

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(mainInteractor: MainInteractor): MainMVPInteractor = mainInteractor
}