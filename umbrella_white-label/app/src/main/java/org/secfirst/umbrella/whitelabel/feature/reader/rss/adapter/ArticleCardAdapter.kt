package org.secfirst.umbrella.whitelabel.feature.reader.rss.adapter

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.einmalfel.earl.Item
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.card_article_view.view.*
import org.jsoup.Jsoup
import org.secfirst.umbrella.whitelabel.R
import org.secfirst.umbrella.whitelabel.data.database.reader.rss.RSS
import org.secfirst.umbrella.whitelabel.misc.convertDateToString
import org.secfirst.umbrella.whitelabel.misc.shareLink

class ArticleCardAdapter(private val onClickLearnMore: (Item) -> Unit) : RecyclerView.Adapter<ArticleCardAdapter.CardHolder>() {

    private lateinit var items: MutableList<Item>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_article_view, parent, false)
        return CardHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.bind(items[position], clickListener = { onClickLearnMore(items[position]) })
    }

    fun addAll(rss: RSS) {
        items = rss.items_.toMutableList()
    }

    class CardHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(articleItem: Item, clickListener: (CardHolder) -> Unit) {
            with(articleItem) {
                val desc = description ?: ""
                itemView.cardTitle.text = title
                itemView.cardDescription.text = Jsoup.parse(desc).text()
                itemView.cardLastUpdate.text = convertDateToString(publicationDate)
                itemView.cardOpenLink.setOnClickListener { clickListener(this@CardHolder) }
                itemView.cardShare.setOnClickListener { itemView.context.shareLink(link!!) }
                Picasso.with(itemView.context)
                        .load(imageLink)
                        .placeholder(ContextCompat.getDrawable(itemView.context, R.drawable.default_image))
                        .into(itemView.cardImage)
            }
        }
    }
}