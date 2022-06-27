package com.example.cryptoobserverapp

import android.app.Application
import androidx.work.Configuration
import com.example.cryptoobserverapp.data.worker.CoinWorkerFactory
import com.example.cryptoobserverapp.di.component.DaggerApplicationComponent
import javax.inject.Inject

class App : Application() , Configuration.Provider {

    @Inject
    lateinit var workerFactory: CoinWorkerFactory

    val component by lazy {
        DaggerApplicationComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }

    override fun getWorkManagerConfiguration(): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }


}