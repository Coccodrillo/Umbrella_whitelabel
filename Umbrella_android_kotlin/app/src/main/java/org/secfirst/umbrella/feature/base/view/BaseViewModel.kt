package org.secfirst.umbrella.feature.base.view

import android.content.Context
import android.widget.RelativeLayout

abstract class BaseViewModel(context: Context) : RelativeLayout(context) {
    abstract fun setUp()
}
