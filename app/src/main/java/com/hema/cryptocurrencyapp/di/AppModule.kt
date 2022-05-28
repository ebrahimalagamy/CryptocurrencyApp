package com.hema.cryptocurrencyapp.di

import com.hema.cryptocurrencyapp.common.Constants
import com.hema.cryptocurrencyapp.data.remote.CoinPaprikaApi
import com.hema.cryptocurrencyapp.data.repository.RepositoryImpl
import com.hema.cryptocurrencyapp.domain.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCoinRepository(api: CoinPaprikaApi): Repository {
        return RepositoryImpl(api)
    }
}