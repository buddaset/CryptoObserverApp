package com.example.cryptoobserverapp.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoobserverapp.domain.repository.CoinRepository
import com.example.cryptoobserverapp.domain.usecase.GetCoinInfoListUseCase
import com.example.cryptoobserverapp.domain.usecase.GetCoinInfoUseCase
import com.example.cryptoobserverapp.domain.usecase.LoadDataUseCase
import com.example.cryptoobserverapp.presentation.coin_detail.CoinDetailsViewModel
import com.example.cryptoobserverapp.presentation.coin_list.CoinListViewModel


class CreateUseCase(private val repository: CoinRepository) {



    fun getCoinIngoUseCase(): GetCoinInfoUseCase = GetCoinInfoUseCase(repository)

    fun loadDataUseCase(): LoadDataUseCase = LoadDataUseCase(repository)

    fun getCoinInfoListUseCase(): GetCoinInfoListUseCase = GetCoinInfoListUseCase(repository)

}

class ViewModelFactory(
    private val getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val getCoinInfoUseCase: GetCoinInfoUseCase,
    private val loadDataUseCase: LoadDataUseCase

) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when (modelClass) {
            CoinListViewModel::class.java -> CoinListViewModel(
                getCoinInfoListUseCase,
                loadDataUseCase
            )
            CoinDetailsViewModel::class.java -> CoinDetailsViewModel(getCoinInfoUseCase)
            else -> throw IllegalStateException("Unknown viewModel")
        } as T
}
