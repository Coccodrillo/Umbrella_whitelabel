package org.secfirst.umbrella.data.internal

import android.content.Context
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File
import java.util.*
import javax.inject.Inject

class TentDatabase @Inject internal constructor(private val context: Context) {

    companion object {
        const val BRANCH_NAME: String = "refs/heads/master"
        const val URI_REPOSITORY = "https://github.com/securityfirst/tent-content.git"
        fun isRepositoryPath(context: Context) = File(context.cacheDir.path + "/repo/").exists()
        fun getPathRepository(context: Context): String = context.cacheDir.path + "/repo/"
    }

    fun initTentRepository() {
        if (!isRepositoryPath(context))
            cloneTentRepository()
                    .subscribeOn(Schedulers.io())
                    //.subscribe { createLocalTentRepository() }
                    .observeOn(Schedulers.newThread())
                    .subscribe { subscriber -> createLocalTentRepository() }


    }

    private fun createLocalTentRepository() =
            Single.fromCallable({
                FileRepositoryBuilder().setGitDir(File(URI_REPOSITORY))
                        .readEnvironment() // scan environment GIT_* variables
                        .findGitDir() // scan up the file system tree
                        .setMustExist(true)
                        .build()
            })


    private fun cloneTentRepository() =
            Single.fromCallable({
                Git.cloneRepository()
                        .setURI(URI_REPOSITORY)
                        .setDirectory(File(getPathRepository(context)))
                        .setBranchesToClone(Arrays.asList(BRANCH_NAME))
                        .setBranch(BRANCH_NAME)
                        .call()
            })
}