package org.secfirst.umbrella.data.internal


import android.content.Context
import javax.inject.Inject


class TentRepository @Inject internal constructor(private val tentDao: TentDao, private val context: Context) : TentRepo {

    override fun getAllTentCategory(): List<Category>? = tentDao.categoryParse(context)
}
