package com.hema.cryptocurrencyapp.presentation.coin_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hema.cryptocurrencyapp.databinding.ItemCoinListBinding
import com.hema.cryptocurrencyapp.domain.model.Coin

class CoinListAdapter : RecyclerView.Adapter<CoinListAdapter.CoinListViewHolder>() {

    var navCallBack: ((Coin) -> Unit)? = null

    var listOfCoins = emptyList<Coin>()
        set(value) {
            field = value
            notifyItemRangeChanged(0, value.size)
        }


    inner class CoinListViewHolder(val binding: ItemCoinListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        operator fun invoke(coin: Coin) {
            binding.apply {
                coin.apply {
                    tvCoinName.text = name
                    tvCoinState.text = if (is_active) "active" else "inactive"
                    item.setOnClickListener { navCallBack?.invoke(this) }
                }
            }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinListViewHolder {
        return CoinListViewHolder(
            ItemCoinListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: CoinListViewHolder, position: Int) {
        holder(listOfCoins[position])
    }

    override fun getItemCount() = listOfCoins.size
}