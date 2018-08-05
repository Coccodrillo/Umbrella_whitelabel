package org.secfirst.whitelabel.feature.content.view

import org.secfirst.whitelabel.feature.base.view.BaseView

interface ContentBaseView : BaseView {

    fun finishDownloadedData()
    fun onErrorContentNotFound()
}