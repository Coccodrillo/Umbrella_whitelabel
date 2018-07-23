package org.secfirst.umbrella.feature.content.view

import org.secfirst.umbrella.feature.base.view.BaseView

interface ContentBaseView : BaseView {

    fun finishDownloadedData()
    fun onErrorContentNotFound()
}