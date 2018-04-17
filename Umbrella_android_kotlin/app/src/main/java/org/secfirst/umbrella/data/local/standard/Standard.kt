package org.secfirst.umbrella.data.local.standard

import com.raizlabs.android.dbflow.annotation.Column
import com.raizlabs.android.dbflow.annotation.PrimaryKey
import com.raizlabs.android.dbflow.annotation.Table
import com.raizlabs.android.dbflow.rx2.structure.BaseRXModel
import org.secfirst.umbrella.data.local.AppDatabase

@Table(name = "standard", database = AppDatabase::class)
class Standard constructor(
        @PrimaryKey(autoincrement = true)
        @Column(name = "id")
        var id: Long = 1,

        @Column(name = "question_text")
        var questionText: String = "") : BaseRXModel()