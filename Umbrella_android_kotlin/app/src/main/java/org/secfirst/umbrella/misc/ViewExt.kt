package org.secfirst.umbrella.misc

import android.support.v7.widget.RecyclerView


fun RecyclerView.initRecyclerView(layoutManager: RecyclerView.LayoutManager,
                                  adapter: RecyclerView.Adapter<out RecyclerView.ViewHolder>,
                                  hasFixedSize: Boolean = true) {
    this.layoutManager = layoutManager
    this.adapter = adapter
    setHasFixedSize(hasFixedSize)
}
