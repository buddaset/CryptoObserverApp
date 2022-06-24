package com.example.cryptoobserverapp.presentation.coin_detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.cryptoobserverapp.App
import com.example.cryptoobserverapp.databinding.FragmentCoinDetailsBinding
import com.example.cryptoobserverapp.domain.model.CoinInfo
import com.example.cryptoobserverapp.presentation.factory.ViewModelFactory
import com.squareup.picasso.Picasso


class CoinDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCoinDetailsBinding
    private  var fSym: String = EMPTY_SYMBOL

    private val viewModel: CoinDetailsViewModel by viewModels {
        ViewModelFactory(
            (requireActivity().application as App) .useCase.getCoinInfoListUseCase() ,
            (requireActivity().application as App).useCase.getCoinIngoUseCase(),
            (requireActivity().application as App).useCase.loadDataUseCase())

    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fSym = requireArguments().getString(EXTRA_FROM_SYMBOL, EMPTY_SYMBOL)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoinDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getDetailsCoinInfoBySymbol(fSym)

        viewModel.coinData.observe(viewLifecycleOwner, this::updateUi)
    }


    private fun updateUi(coin: CoinInfo) = with(binding){
        tvPrice.text = coin.price
        tvMinPrice.text = coin.lowDay
        tvMaxPrice.text = coin.highDay
        tvLastMarket.text = coin.lastMarket
        tvLastUpdate.text = coin.lastUpdate
        tvFromSymbol.text = coin.fromSymbol
        tvToSymbol.text = coin.toSymbol
        Picasso.get().load(coin.imageUrl).into(ivLogoCoin)


    }

    companion object {

        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newInstance(fromSymbol: String): Fragment {
            return CoinDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_FROM_SYMBOL, fromSymbol)
                }
            }
        }
    }

}