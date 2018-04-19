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
        initRealmDatabase()
    }

    private fun initDaggerComponent() {
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    private fun initRealmDatabase() {
        Realm.init(this)
        val config = RealmConfiguration.Builder()
                //The Realm file can be encrypted on disk by passing a 512-bit encryption key (64 bytes)
                .encryptionKey("aaljdkfjkadsfçldsklfjalksdjfkljasdfkjakdsfasdflkjiiepeieippwowo".toByteArray())
                .name("umbrella.realm").build()
        Realm.setDefaultConfiguration(config)

    }
}     