package org.secfirst.umbrella.whitelabel.data.database.reader.rss

import com.google.gson.annotations.SerializedName
import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import org.secfirst.umbrella.whitelabel.data.database.AppDatabase


data class RefRSS(var items: MutableList<RefRSSItem> = mutableListOf())

@Table(database = AppDatabase::class)
data class RefRSSItem(
        @PrimaryKey(autoincrement = true)
        var id: Long = 0,
        @Column
        @SerializedName("link")
        var url: String = "") {
    constructor(url: String = "") : this(0, url)
}

const val RSS_FILE_NAME: String = "default_rss.json"

