package org.secfirst.umbrella.whitelabel.feature.reader.rss

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.einmalfel.earl.Feed
import kotlinx.android.synthetic.main.rss_item_view.view.*
import org.secfirst.umbrella.whitelabel.R

class RssAdapter : RecyclerView.Adapter<RssAdapter.RssHolder>() {

    private val rssList: MutableList<Feed> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RssHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rss_item_view, parent, false)
        return RssHolder(view)
    }

    override fun getItemCount() = rssList.size

    override fun onBindViewHolder(holder: RssHolder, position: Int) {
        holder.bind(rssList[position])
    }

    fun addAll(feedList: List<Feed>) {
        feedList.forEach { rssList.add(it) }
        notifyDataSetChanged()
    }

    class RssHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(feed: Feed) {
            with(feed) {
                itemView.rssTitle.text = title
                itemView.rssDescription.text = description
            }
        }
    }
}

