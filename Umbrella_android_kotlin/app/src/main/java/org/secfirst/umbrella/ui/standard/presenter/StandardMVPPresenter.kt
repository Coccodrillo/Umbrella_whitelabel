package org.secfirst.umbrella.ui.standard.presenter

import org.secfirst.umbrella.ui.base.presenter.MVPPresenter
import org.secfirst.umbrella.ui.standard.interactor.StandardMVPInteractor
import org.secfirst.umbrella.ui.standard.view.StandardMVPView

interface StandardMVPPresenter<V : StandardMVPView, I : StandardMVPInteractor> : MVPPresenter<V, I>