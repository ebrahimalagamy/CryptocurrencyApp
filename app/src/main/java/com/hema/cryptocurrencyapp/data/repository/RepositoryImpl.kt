package com.hema.cryptocurrencyapp.data.repository

import com.hema.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.hema.cryptocurrencyapp.data.remote.dto.CoinDetailDto
import com.hema.cryptocurrencyapp.data.remote.dto.CoinDto
import com.hema.cryptocurrencyapp.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi
) : Repository {
    override suspend fun getCoins(): List<CoinDto> {
        return api.getCoins()
    }

    override suspend fun getCoinById(coinId: String): CoinDetailDto {
        return api.getCoinById(coinId)
    }
}