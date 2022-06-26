package com.example.cryptoobserverapp.di.component

import com.example.cryptoobserverapp.presentation.coin_list.CoinListFragment
import dagger.Component


@Component
interface ApplicationComponent {


    fun inject(fragment: CoinListFragment)


    interface ApplicationComponentFactory {


        fun create(): ApplicationComponent
    }
}