package com.example.cryptoobserverapp.di.module

import androidx.lifecycle.ViewModel
import com.example.cryptoobserverapp.di.key.ViewModelKey
import com.example.cryptoobserverapp.domain.usecase.GetCoinInfoListUseCase
import com.example.cryptoobserverapp.domain.usecase.GetCoinInfoUseCase
import com.example.cryptoobserverapp.domain.usecase.LoadDataUseCase
import com.example.cryptoobserverapp.presentation.coin_detail.CoinDetailsViewModel
import com.example.cryptoobserverapp.presentation.coin_list.CoinListViewModel
import com.example.cryptoobserverapp.presentation.factory.ViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Provider


@Module
class ViewModelModule {


    @Provides
    fun provideViewModelFactory(viewModelProviders: @JvmSuppressWildcards Map<Class<out ViewModel>, Provider<ViewModel>>): ViewModelFactory =
        ViewModelFactory(viewModelProviders)

    @IntoMap
    @ViewModelKey(CoinDetailsViewModel::class)
    @Provides
    fun provideCoinDetailsViewModel(getCoinInfoUseCase: GetCoinInfoUseCase) : ViewModel =
        CoinDetailsViewModel(getCoinInfoUseCase)


    @IntoMap
    @ViewModelKey(CoinListViewModel::class)
    @Provides
    fun provideCoinListViewModel(getCoinInfoListUseCase: GetCoinInfoListUseCase, loadDataUseCase: LoadDataUseCase) : ViewModel =
        CoinListViewModel(getCoinInfoListUseCase, loadDataUseCase)

}