package org.secfirst.umbrella.data.database.form

import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import io.reactivex.Single
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value

interface FormDao {

    fun insertDataForm(formData: Value) { modelAdapter<Value>().insert(formData) }

    fun getAllModel(): Single<List<Form>> = Single.just(SQLite.select().from(Form::class.java).queryList())
}