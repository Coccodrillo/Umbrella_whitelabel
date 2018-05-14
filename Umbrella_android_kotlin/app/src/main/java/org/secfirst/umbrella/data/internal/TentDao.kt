package org.secfirst.umbrella.data.internal

import android.content.Context
import android.util.Log
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File
import java.util.*


interface TentDao {

    companion object {
        private const val BRANCH_NAME: String = "refs/heads/master"
        private const val URI_REPOSITORY = "https://github.com/klaidliadon/tent-sample.git"
    }

    private fun isRepositoryPath(context: Context) = File(context.cacheDir.path + "/repo/").exists()
    private fun getPathRepository(context: Context): String = context.cacheDir.path + "/repo/"
    private fun getRepository(): Repository =
            FileRepositoryBuilder().setGitDir(File(URI_REPOSITORY))
                    .readEnvironment() // scan environment GIT_* variables
                    .findGitDir() // scan up the file system tree
                    .setMustExist(true)
                    .build()

    private fun getGitTentInstance(context: Context): Git = if (!isRepositoryPath(context))
        Git.cloneRepository()
                .setURI(URI_REPOSITORY)
                .setDirectory(File(getPathRepository(context)))
                .setBranchesToClone(Arrays.asList(BRANCH_NAME))
                .setBranch(BRANCH_NAME)
                .call()
    else Git.open(File(getPathRepository(context)))

    fun categoryParse(context: Context) {
        val git: Git = getGitTentInstance(context)
    }
}