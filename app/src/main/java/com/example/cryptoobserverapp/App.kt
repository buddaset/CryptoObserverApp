package com.example.cryptoobserverapp

import android.app.Application
import com.example.cryptoobserverapp.di.component.DaggerApplicationComponent

class App : Application() {

    val component by lazy {
        DaggerApplicationComponent.factory()
            .create(applicationContext)

    }







}