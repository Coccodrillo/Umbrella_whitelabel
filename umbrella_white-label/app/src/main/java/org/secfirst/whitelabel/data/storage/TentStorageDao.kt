package org.secfirst.whitelabel.data.storage

import io.reactivex.Single
import org.eclipse.jgit.api.Git
import java.io.File
import java.util.*

interface TentStorageDao {

    fun cloneRepository(tentConfig: TentConfig): Single<Boolean> = cloneTentRepository(tentConfig)

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

    private fun cloneTentRepository(tentConfig: TentConfig): Single<Boolean> {
        var result = false
        if (tentConfig.isNotCreate()) {
            Git.cloneRepository()
                    .setURI(TentConfig.URI_REPOSITORY)
                    .setDirectory(File(tentConfig.getPathRepository()))
                    .setBranchesToClone(Arrays.asList(TentConfig.BRANCH_NAME))
                    .setBranch(TentConfig.BRANCH_NAME)
                    .call()
            result = true
        }
        return Single.just(result)
    }
}