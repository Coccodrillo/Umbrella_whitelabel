package org.secfirst.umbrella.data.local.standard

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "standard_table")
data class Standard(
        @Expose
        @PrimaryKey
        var id: Long,

        @Expose
        @SerializedName("question_text")
        @ColumnInfo(name = "question_text")
        var questionText: String


)