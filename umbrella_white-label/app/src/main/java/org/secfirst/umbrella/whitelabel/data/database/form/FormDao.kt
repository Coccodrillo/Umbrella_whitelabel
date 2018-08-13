package org.secfirst.umbrella.whitelabel.data.database.form

import com.raizlabs.android.dbflow.kotlinextensions.modelAdapter
import com.raizlabs.android.dbflow.sql.language.SQLite
import kotlinx.coroutines.experimental.withContext
import org.secfirst.umbrella.whitelabel.data.*
import org.secfirst.umbrella.whitelabel.misc.AppExecutors.Companion.ioContext


interface FormDao {

    suspend fun insertAnswer(answer: Answer) {
        withContext(ioContext) {
            modelAdapter<Answer>().insert(answer)
        }
    }

    suspend fun delete(activeForm: ActiveForm) {
        withContext(ioContext) {
            modelAdapter<ActiveForm>().delete(activeForm)
        }
    }

    suspend fun saveActiveForm(activeForm: ActiveForm) {
        withContext(ioContext) {
            modelAdapter<ActiveForm>().save(activeForm)
        }
    }

    suspend fun getAnswerBy(formId: Long): List<Answer> = withContext(ioContext) {
        withContext(ioContext) {
            SQLite.select()
                    .from(Answer::class.java)
                    .where(Answer_Table.activeForm_id.`is`(formId))
                    .queryList()
        }
    }

    suspend fun getAllFormModel(): List<Form> = withContext(ioContext) {
        withContext(ioContext) {
            SQLite.select()
                    .from(Form::class.java)
                    .queryList()
        }
    }

    suspend fun getAllActiveForms(): List<ActiveForm> = withContext(ioContext) {
        withContext(ioContext) {
            SQLite.select()
                    .from(ActiveForm::class.java)
                    .queryList()
        }
    }

    suspend fun getScreenBy(formId: Long): List<Screen> = withContext(ioContext) {
        withContext(ioContext) {
            SQLite.select()
                    .from(Screen::class.java)
                    .where(Screen_Table.form_id.`is`(formId))
                    .queryList()
        }
    }
}