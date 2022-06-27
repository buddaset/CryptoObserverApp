package com.example.cryptoobserverapp.di.component

import android.content.Context
import com.example.cryptoobserverapp.App
import com.example.cryptoobserverapp.di.module.DataModule
import com.example.cryptoobserverapp.di.module.DomainModule
import com.example.cryptoobserverapp.di.module.WorkerModule
import com.example.cryptoobserverapp.di.scope.ApplicationScope
import com.example.cryptoobserverapp.presentation.coin_list.CoinListFragment
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [DomainModule::class, DataModule::class, WorkerModule::class])
interface ApplicationComponent {


    fun fragmentComponentFactory() : FragmentComponent.Factory

    fun inject(application: App)



    @Component.Factory
    interface ApplicationComponentFactory {


        fun create(
            @BindsInstance appContext: Context
        ): ApplicationComponent
    }
}