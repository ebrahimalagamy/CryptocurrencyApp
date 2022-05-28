package com.hema.cryptocurrencyapp.domain.use_case.get_coin

import com.hema.cryptocurrencyapp.common.Resource
import com.hema.cryptocurrencyapp.data.remote.dto.toCoin
import com.hema.cryptocurrencyapp.data.remote.dto.toCoinDetail
import com.hema.cryptocurrencyapp.domain.model.Coin
import com.hema.cryptocurrencyapp.domain.model.CoinDetail
import com.hema.cryptocurrencyapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(coinId:String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId).toCoinDetail()
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Un Expected Error"))
        } catch (e: IOException) {
            emit(Resource.Error("No Internet Connection"))

        }

    }
}