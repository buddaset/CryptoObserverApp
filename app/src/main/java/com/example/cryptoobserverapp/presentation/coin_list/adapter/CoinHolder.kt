package com.example.cryptoobserverapp.presentation.coin_list.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoobserverapp.R
import com.example.cryptoobserverapp.databinding.CoinItemBinding
import com.example.cryptoobserverapp.domain.model.CoinInfo
import com.squareup.picasso.Picasso


class CoinHolder(
    private val binding: CoinItemBinding,
    private val onClick: clickCoinListener
) : RecyclerView.ViewHolder(binding.root) {

    private val context = itemView.context


    fun bind(coin: CoinInfo) = with(binding) {

        symbolTextView.text =
            context.getString(R.string.symbols_template, coin.fromSymbol, coin.toSymbol)
        priceTextView.text = coin.price
        lastUpdateTextView.text = context.getString(R.string.last_update_template, coin.lastUpdate)
        Picasso.get().load(coin.imageUrl).into(imageLogo)

        root.setOnClickListener {
            onClick(coin)
        }


    }
}