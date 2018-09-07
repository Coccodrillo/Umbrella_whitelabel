package org.secfirst.umbrella.whitelabel.data.database.reader.rss

import com.einmalfel.earl.Feed
import com.einmalfel.earl.Item
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import org.secfirst.umbrella.whitelabel.data.database.AppDatabase
import java.util.*


data class RefRSS(var items: MutableList<RefRSSItem> = mutableListOf())

data class RefRSSItem(val link: String)

@Table(database = AppDatabase::class)
data class RSS(@PrimaryKey
               var url_: String = "", var title_: String = "",
               var description_: String = "", var publicationDate_: Date = Date(),
               var imageLink_: String = "", var copyRight_: String = "",
               var author_: String = "", var items_: MutableList<out Item> = mutableListOf()) : Feed {


    override fun getLink(): String = url_

    override fun getImageLink(): String = imageLink_

    override fun getItems(): MutableList<out Item> = items_

    override fun getCopyright() = author

    override fun getDescription(): String = description_

    override fun getTitle(): String = title_

    override fun getAuthor(): String = author_

    override fun getPublicationDate(): Date = publicationDate_

    constructor(link: String = "") : this(link, "", "", Date(), "", "", "")

}

val Feed.convertToRSS: RSS
    get() {
        val rss = RSS()
        rss.url_ = this.link ?: ""
        rss.title_ = this.title
        rss.description_ = this.description ?: ""
        rss.publicationDate_ = this.publicationDate ?: Date()
        rss.imageLink_ = this.imageLink ?: ""
        rss.copyRight_ = this.copyright ?: ""
        rss.author_ = this.author ?: ""
        rss.items_ = this.items
        return rss
    }
const val RSS_FILE_NAME: String = "default_rss.json"

