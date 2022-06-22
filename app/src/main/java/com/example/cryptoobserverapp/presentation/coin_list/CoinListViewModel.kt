package com.example.cryptoobserverapp.presentation.coin_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoobserverapp.domain.model.CoinInfo
import com.example.cryptoobserverapp.domain.usecase.GetCoinInfoListUseCase
import com.example.cryptoobserverapp.domain.usecase.GetCoinInfoUseCase
import com.example.cryptoobserverapp.domain.usecase.LoadDataUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CoinListViewModel(
     getCoinInfoListUseCase: GetCoinInfoListUseCase,
    private val loadDataUseCase: LoadDataUseCase
): ViewModel() {

    init {
        viewModelScope.launch {
            loadDataUseCase()

        }
    }


    val coins: StateFlow<List<CoinInfo>> = getCoinInfoListUseCase()
        .stateIn(viewModelScope,  SharingStarted.WhileSubscribed(5000) ,  listOf() )



}