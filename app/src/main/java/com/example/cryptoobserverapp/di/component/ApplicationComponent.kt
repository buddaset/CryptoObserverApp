package com.example.cryptoobserverapp.di.component

import android.content.Context
import com.example.cryptoobserverapp.di.module.DataModule
import com.example.cryptoobserverapp.di.module.DomainModule
import com.example.cryptoobserverapp.presentation.coin_list.CoinListFragment
import dagger.BindsInstance
import dagger.Component


@Component(modules = [DomainModule::class, DataModule::class])
interface ApplicationComponent {


    fun fragmentComponentFactory() : FragmentComponent.Factory


    @Component.Factory
    interface ApplicationComponentFactory {


        fun create(
            @BindsInstance appContext: Context
        ): ApplicationComponent
    }
}