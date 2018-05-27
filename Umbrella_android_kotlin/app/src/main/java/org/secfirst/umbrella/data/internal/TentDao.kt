package org.secfirst.umbrella.data.internal

import android.content.Context
import android.util.Log
import org.eclipse.jgit.api.Git
import java.io.File


interface TentDao {

    fun categoryParse(context: Context): List<Category>? {
        val git = Git.open(File(TentDatabase.getPathRepository(context))).checkout()
        val repository = git.getRepository()
        Log.e("test", "deu bom.")
        return null
    }
}