package org.secfirst.umbrella.whitelabel.feature.content.view

import org.secfirst.umbrella.whitelabel.feature.base.view.BaseView

interface ContentBaseView : BaseView {

    fun finishDownloadedData()
    fun onErrorContentNotFound()
}