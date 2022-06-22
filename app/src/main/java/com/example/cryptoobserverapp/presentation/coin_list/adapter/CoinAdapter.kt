package com.example.cryptoobserverapp.presentation.coin_list.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.cryptoobserverapp.databinding.CoinItemBinding
import com.example.cryptoobserverapp.domain.model.CoinInfo


typealias clickCoinListener = (CoinInfo) -> Unit

class CoinAdapter(private val clickCoinListener: clickCoinListener) : ListAdapter<CoinInfo,CoinHolder>(
    DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinHolder =
        CoinHolder( CoinItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false),
            clickCoinListener)



    override fun onBindViewHolder(holder: CoinHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CoinInfo>() {
            override fun areItemsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean =
                oldItem.fromSymbol == newItem.fromSymbol


            override fun areContentsTheSame(oldItem: CoinInfo, newItem: CoinInfo): Boolean =
                oldItem == newItem



        }
    }
}