package org.secfirst.umbrella.ui.standard.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.secfirst.umbrella.R

class StandardFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_standard, container, false)


    companion object {
        fun newInstance(): StandardFragment = StandardFragment()
    }
}