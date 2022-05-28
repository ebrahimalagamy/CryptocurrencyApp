package com.hema.cryptocurrencyapp.presentation.coin_details

import com.hema.cryptocurrencyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coins: CoinDetail? = null,
    val error: String = ""

)