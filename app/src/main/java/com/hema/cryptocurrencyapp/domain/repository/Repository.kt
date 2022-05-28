package com.hema.cryptocurrencyapp.domain.repository

import com.hema.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.hema.cryptocurrencyapp.data.remote.dto.CoinDto

interface Repository {
    suspend fun getCoins(): List<CoinDto>
    suspend fun getCoinById(coinId: String): CoinDetailDto
}