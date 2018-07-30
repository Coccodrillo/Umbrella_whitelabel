package org.secfirst.umbrella.data.database.form

import org.secfirst.umbrella.data.Form
import org.secfirst.umbrella.data.Value

interface FormRepo {

    suspend fun persist(formData: Value)

    suspend fun getAll(): List<Form>

    suspend fun loadBy(id: Long): List<Value>

    suspend fun loadDataBy(forms: List<Form>): List<Form>
}