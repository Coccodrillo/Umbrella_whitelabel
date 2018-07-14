package org.secfirst.umbrella.feature.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bluelinelabs.conductor.Controller
import org.secfirst.umbrella.R


class FeedController : Controller() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val view = inflater.inflate(R.layout.feed_view, container, false)
        return view
    }
}

