package org.secfirst.core.storage

import android.util.Log
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File
import java.util.*

interface TentStorageDao {

    fun cloneRepository(tentConfig: TentConfig) {
        if (!tentConfig.isRepositoryPath())
            cloneTentRepository(tentConfig)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { _ ->
                        Log.i("test", "Tent repository was cloned.")
                        createLocalTentRepository()
                    }
    }

    private fun createLocalTentRepository() =
            Single.fromCallable({
                FileRepositoryBuilder().setGitDir(File(TentConfig.URI_REPOSITORY))
                        .readEnvironment() // scan environment GIT_* variables
                        .findGitDir() // scan up the file system tree
                        .setMustExist(true)
                        .build()
            }).doAfterSuccess { Log.i("test", "Local Tent repository was created.") }


    private fun cloneTentRepository(tentConfig: TentConfig) =
            Single.fromCallable({
                Git.cloneRepository()
                        .setURI(TentConfig.URI_REPOSITORY)
                        .setDirectory(File(tentConfig.getPathRepository()))
                        .setBranchesToClone(Arrays.asList(TentConfig.BRANCH_NAME))
                        .setBranch(TentConfig.BRANCH_NAME)
                        .call()
            })

    fun tentSerialize(tentConfig: TentConfig) = CategoryAdapter(tentConfig).serialize()
}