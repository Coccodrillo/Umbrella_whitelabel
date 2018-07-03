package org.secfirst.umbrella.data.storage

import io.reactivex.Single
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.storage.file.FileRepositoryBuilder
import java.io.File
import java.util.*

interface TentStorageDao {

    fun cloneRepository(tentConfig: TentConfig): Single<Git> = cloneTentRepository(tentConfig)

    fun filterByElement(tentConfig: TentConfig): List<File> {
        val files: MutableList<File> = arrayListOf()
        File(tentConfig.getPathRepository())
                .walk()
                .filter { file -> !file.path.contains(".git") }
                .filter { file ->
                    TentConfig.getDelimiter(file.name) == TypeFile.SEGMENT.value
                            || TentConfig.getDelimiter(file.name) == TypeFile.CHECKLIST.value
                            || TentConfig.getDelimiter(file.name) == TypeFile.FORM.value
                }
                .filter { it.isFile }
                .forEach { file ->
                    files.add(file)
                }
        files.reverse()
        return files
    }

    fun filterBySubElement(tentConfig: TentConfig): List<File> {
        val files: MutableList<File> = arrayListOf()
        File(tentConfig.getPathRepository())
                .walk()
                .filter { file -> !file.path.contains(".git") }
                .filter { file -> file.name == ".category.yml" }
                .filter { it.isFile }
                .forEach { file -> files.add(file) }
        files.reverse()
        return files
    }

    private fun createLocalTentRepository(path: String) =
            Single.fromCallable {
                FileRepositoryBuilder().setGitDir(File(path))
                        .readEnvironment() // scan environment GIT_* variables
                        .findGitDir() // scan up the file system tree
                        .setMustExist(true)
                        .build()
            }.subscribe()

    private fun cloneTentRepository(tentConfig: TentConfig): Single<Git> {
        return if (tentConfig.isNotRepositoryPath())
            Single.fromCallable {
                Git.cloneRepository()
                        .setURI(TentConfig.URI_REPOSITORY)
                        .setDirectory(File(tentConfig.getPathRepository()))
                        .setBranchesToClone(Arrays.asList(TentConfig.BRANCH_NAME))
                        .setBranch(TentConfig.BRANCH_NAME)
                        .call()
            }
        else
            Single.just(Git.open(File(tentConfig.getPathRepository())))
    }
}