package org.secfirst.umbrella

import android.app.Activity
import android.app.Application
import com.facebook.stetho.Stetho
import com.raizlabs.android.dbflow.config.DatabaseConfig
import com.raizlabs.android.dbflow.config.FlowConfig
import com.raizlabs.android.dbflow.config.FlowLog
import com.raizlabs.android.dbflow.config.FlowManager
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.sqlcipher.database.SQLiteDatabase
import org.secfirst.umbrella.data.database.AppDatabase
import org.secfirst.umbrella.data.database.SQLCipherHelperImpl
import org.secfirst.umbrella.di.component.DaggerAppComponent
import javax.inject.Inject


class UmbrellaApplication : Application(), HasActivityInjector {

    @Inject
    internal lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector() = activityDispatchingAndroidInjector

    companion object {
        lateinit var instance: UmbrellaApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDaggerComponent()
        initDatabase()
        initTentRepository()
        initStetho()

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

    private fun initStetho() {
        Stetho.initializeWithDefaults(this);
    }

    private fun initTentRepository() {

    }

    override fun onTerminate() {
        super.onTerminate()
        FlowManager.destroy()
    }
}
