package org.secfirst.umbrella

import android.app.Activity
import android.app.Application
import com.raizlabs.android.dbflow.config.DatabaseConfig
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowLog
import com.raizlabs.android.dbflow.config.FlowManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.sqlcipher.database.SQLiteDatabase
import org.eclipse.jgit.api.Git
import org.secfirst.umbrella.data.database.AppDatabase
import org.secfirst.umbrella.data.database.SQLCipherHelperImpl
import org.secfirst.umbrella.di.component.DaggerAppComponent
import java.io.File
import java.util.*
import javax.inject.Inject
import org.eclipse.jgit.storage.file.FileRepositoryBuilder






class UmbrellaApplication : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    override fun activityInjector() = activityDispatchingAndroidInjector

    override fun onCreate() {
        super.onCreate()

        initDaggerComponent()
        initDatabase()
    }

    private fun initDaggerComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    private fun initDatabase() {
        SQLiteDatabase.loadLibs(this)
        val dbConfig = FlowConfig.Builder(this)
                .addDatabaseConfig(DatabaseConfig
                        .Builder(AppDatabase::class.java)
                        .databaseName(AppDatabase.NAME)
                        .openHelper { databaseDefinition, helperListener ->
                            SQLCipherHelperImpl(databaseDefinition, helperListener)
                        }
                        .build())
                .build()

        FlowManager.init(dbConfig)
        FlowLog.setMinimumLoggingLevel(FlowLog.Level.V)
    }

    private fun initTentRepository() {
       val git =  Git.cloneRepository()
                .setURI("https://github.com/klaidliadon/tent-sample.git")
                .setDirectory(File(applicationContext.cacheDir.path + "/repo/"))
                .setBranchesToClone(Arrays.asList("refs/heads/master"))
                .setBranch("refs/heads/master")
                .call()

        val repositoryBuilder = FileRepositoryBuilder()
        val repository = repositoryBuilder.setGitDir(File("/path/to/repo/.git"))
                .readEnvironment() // scan environment GIT_* variables
                .findGitDir() // scan up the file system tree
                .setMustExist(true)
                .build()
        Git.open(File("/path/to/repo/.git"))
                .checkout()
        val te = git.getRepository()
    }

    override fun onTerminate() {
        super.onTerminate()
        FlowManager.destroy()
    }
}
