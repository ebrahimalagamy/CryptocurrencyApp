package com.hema.cryptocurrencyapp.domain.model

import com.hema.cryptocurrencyapp.data.remote.dto.Team

data class CoinDetail(
    val coinId: String,
    val name: String,
    val description: String,
    val symbol: String,
    val rank: Int,
    val isActive: Boolean,
    val tag: List<String>,
    val team: List<Team>
)
