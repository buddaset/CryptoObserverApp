package com.example.cryptoobserverapp.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoobserverapp.domain.repository.CoinRepository
import com.example.cryptoobserverapp.domain.usecase.GetCoinInfoListUseCase
import com.example.cryptoobserverapp.domain.usecase.GetCoinInfoUseCase
import com.example.cryptoobserverapp.domain.usecase.LoadDataUseCase
import com.example.cryptoobserverapp.presentation.coin_detail.CoinDetailsViewModel
import com.example.cryptoobserverapp.presentation.coin_list.CoinListViewModel
import javax.inject.Provider
import kotlin.reflect.KClass


class ViewModelFactory(private val  viewModelProviders: Map<Class<out ViewModel>, Provider<ViewModel>>


) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        viewModelProviders[modelClass]?.get() as T

}
