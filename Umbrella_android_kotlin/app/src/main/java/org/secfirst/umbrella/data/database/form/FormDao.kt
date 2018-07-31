package org.secfirst.umbrella.data.database.form

import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.coroutines.experimental.withContext
import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value
import org.secfirst.umbrella.data.Value_Table
import org.secfirst.umbrella.misc.AppExecutors.Companion.ioContext


interface FormDao {

    suspend fun insertDataForm(formData: Value) {
        modelAdapter<Value>().insert(formData)
    }


    suspend fun getAllFormModel(): List<Form> = withContext(ioContext) {
        SQLite.select()
                .from(Form::class.java)
                .queryList()
    }


    suspend fun getDataFormById(id: Long): List<Value> =
            withContext(ioContext) {
                SQLite.select()
                        .from(Value::class.java)
                        .where(Value_Table.form_id.`is`(id))
                        .queryList()

            }

    suspend fun getAllDataFormBy(forms: List<Form>) =
            withContext(ioContext) {
                val formsWithData = mutableListOf<Form>()
                forms.forEach { form ->
                    val value = getDataFormById(form.id)
                    if (value.isNotEmpty()) {
                        form.data = value
                        formsWithData.add(form)
                    }
                }
                return@withContext formsWithData
            }
}