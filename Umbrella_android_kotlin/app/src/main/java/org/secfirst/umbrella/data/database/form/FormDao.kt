package org.secfirst.umbrella.data.database.form

import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value
import org.secfirst.umbrella.data.Value_Table

interface FormDao {

    fun insertDataForm(formData: Value) {
        modelAdapter<Value>().insert(formData)
    }

    fun getAllModel(): List<Form> =
            SQLite.select()
                    .from(Form::class.java)
                    .queryList()

    fun getDataFormById(id: Long): List<Value> =
            SQLite.select()
                    .from(Value::class.java)
                    .where(Value_Table.form_id.`is`(id))
                    .queryList()
}