package org.secfirst.umbrella.feature.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import org.secfirst.umbrella.R


class SampleController : Controller() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.activity_main, container, false)
        return view
    }
}

