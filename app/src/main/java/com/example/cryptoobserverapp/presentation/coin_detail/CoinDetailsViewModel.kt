package com.example.cryptoobserverapp.presentation.coin_detail

import androidx.lifecycle.*
import com.example.cryptoobserverapp.domain.model.CoinInfo
import com.example.cryptoobserverapp.domain.usecase.GetCoinInfoUseCase

class CoinDetailsViewModel(private val getCoinInfoUseCase: GetCoinInfoUseCase) : ViewModel() {


    private val fromSymbol = MutableLiveData<String>()

    val coinData: LiveData<CoinInfo> = fromSymbol.switchMap{ symbol ->
        liveData {
            emit(getCoinInfoUseCase(symbol))
        }

    }



    fun getDetailsCoinInfoBySymbol(fSym: String) {
        fromSymbol.value = fSym
    }

}