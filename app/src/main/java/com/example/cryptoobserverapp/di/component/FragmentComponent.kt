package com.example.cryptoobserverapp.di.component

import com.example.cryptoobserverapp.di.module.ViewModelModule
import com.example.cryptoobserverapp.presentation.coin_detail.CoinDetailsFragment
import com.example.cryptoobserverapp.presentation.coin_list.CoinListFragment
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class])
interface FragmentComponent {


    fun inject(fragment: CoinDetailsFragment)
    fun inject(fragment: CoinListFragment)

    @Subcomponent.Factory
    interface Factory {

        fun create(): FragmentComponent

    }


}