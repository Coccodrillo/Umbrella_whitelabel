package org.secfirst.core.storage

import io.reactivex.Single
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File
import java.util.*

interface TentStorageDao {

    fun cloneRepository(tentConfig: TentConfig): Single<Git> = cloneTentRepository(tentConfig)

    private fun createLocalTentRepository() =
            Single.fromCallable({
                FileRepositoryBuilder().setGitDir(File(TentConfig.URI_REPOSITORY))
                        .readEnvironment() // scan environment GIT_* variables
                        .findGitDir() // scan up the file system tree
                        .setMustExist(true)
                        .build()
            }).subscribe()


    private fun cloneTentRepository(tentConfig: TentConfig): Single<Git> {

        return if (tentConfig.isNotRepositoryPath())
            Single.fromCallable({
                Git.cloneRepository()
                        .setURI(TentConfig.URI_REPOSITORY)
                        .setDirectory(File(tentConfig.getPathRepository()))
                        .setBranchesToClone(Arrays.asList(TentConfig.BRANCH_NAME))
                        .setBranch(TentConfig.BRANCH_NAME)
                        .call()
            })
        else
            Single.just(Git.open(File(tentConfig.getPathRepository())))
    }


    fun tentSerialize(tentConfig: TentConfig) = CategoryAdapter(tentConfig).serialize()
}