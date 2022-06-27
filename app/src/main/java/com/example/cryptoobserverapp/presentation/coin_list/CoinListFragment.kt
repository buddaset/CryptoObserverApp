package com.example.cryptoobserverapp.presentation.coin_list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoobserverapp.App
import com.example.cryptoobserverapp.R
import com.example.cryptoobserverapp.databinding.FragmentCoinListBinding
import com.example.cryptoobserverapp.domain.model.CoinInfo
import com.example.cryptoobserverapp.presentation.coin_detail.CoinDetailsViewModel
import com.example.cryptoobserverapp.presentation.coin_list.adapter.CoinAdapter
import com.example.cryptoobserverapp.presentation.extension.collectFlow
import com.example.cryptoobserverapp.presentation.factory.ViewModelFactory
import com.example.cryptoobserverapp.presentation.main.navigation.navigator
import javax.inject.Inject

class CoinListFragment : Fragment() {

    private lateinit var binding: FragmentCoinListBinding

    private lateinit var coinAdapter: CoinAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: CoinListViewModel by viewModels { viewModelFactory }

    private val component by lazy {
        (requireActivity().application as App).component
    }

    override fun onAttach(context: Context) {
        component.fragmentComponentFactory().create().inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCoinListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        collectData()

    }


    private fun setupAdapter() {

        coinAdapter = CoinAdapter(::onClickCoinListener)
        binding.coinRecycler.adapter = coinAdapter
        binding.coinRecycler.layoutManager = LinearLayoutManager(context)

    }

    private fun collectData() {

        collectFlow(viewModel.coins) {
            coinAdapter.submitList(it)
        }
    }


    private fun onClickCoinListener(coin: CoinInfo) {
        navigator().showCoinDetailScreen(coin.fromSymbol)


    }

    companion object {
        fun newInstance() = CoinListFragment()
    }


}