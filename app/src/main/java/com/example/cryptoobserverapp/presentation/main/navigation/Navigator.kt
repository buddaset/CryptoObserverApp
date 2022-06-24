package com.example.cryptoobserverapp.presentation.main.navigation

import androidx.fragment.app.Fragment


fun Fragment.navigator(): Navigator =
    (requireActivity() as Navigator)

interface Navigator {


    fun showCoinDetailScreen(fSym: String)

    fun goBack()
}