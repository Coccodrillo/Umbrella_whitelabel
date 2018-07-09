package org.secfirst.umbrella.util

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

internal fun FragmentManager.removeFragment(tag: String) {
    this.beginTransaction()
            .disallowAddToBackStack()
            .remove(this.findFragmentByTag(tag))
            .commitNow()
}

internal fun FragmentManager.addOrReplaceFragment(containerViewId: Int, fragment: Fragment, tag: String) {
    this.beginTransaction()
            .replace(containerViewId, fragment, tag)
            .addToBackStack(null)
            .commit()
}

