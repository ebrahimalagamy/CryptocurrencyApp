package com.hema.cryptocurrencyapp.domain.use_case.get_coins

import com.hema.cryptocurrencyapp.common.Resource
import com.hema.cryptocurrencyapp.data.remote.dto.toCoin
import com.hema.cryptocurrencyapp.domain.model.Coin
import com.hema.cryptocurrencyapp.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoins().map { it.toCoin() }
            emit(Resource.Success(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "Un Expected Error"))
        } catch (e: IOException) {
            emit(Resource.Error("No Internet Connection"))

        }

    }
}