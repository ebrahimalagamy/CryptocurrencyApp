package com.hema.cryptocurrencyapp.presentation.coin_list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hema.cryptocurrencyapp.common.Resource
import com.hema.cryptocurrencyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
) : ViewModel() {

    private val _stateMutableLiveData = MutableLiveData(CoinListState())
    val sateLiveData: LiveData<CoinListState> = _stateMutableLiveData

    init {
        getCoins()
    }

    private fun getCoins() {
        getCoinsUseCase().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _stateMutableLiveData.value = CoinListState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateMutableLiveData.value = CoinListState(coins = result.data ?: emptyList())
                    Log.e("data", result.data.toString())
                }
                is Resource.Error -> {
                    _stateMutableLiveData.value =
                        CoinListState(error = result.message ?: "Un Expected Error")

                }
            }

        }.launchIn(viewModelScope)
    }

}