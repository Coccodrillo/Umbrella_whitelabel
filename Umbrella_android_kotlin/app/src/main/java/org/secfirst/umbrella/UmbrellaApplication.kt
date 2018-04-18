package org.secfirst.umbrella

import android.app.Activity
import android.app.Application
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import org.secfirst.umbrella.di.component.DaggerAppComponent
import javax.inject.Inject


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
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("umbrella.db").build()
        Realm.setDefaultConfiguration(config)
    }
}