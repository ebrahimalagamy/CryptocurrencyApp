package com.hema.cryptocurrencyapp.presentation.coin_details

import androidx.lifecycle.*
import com.hema.cryptocurrencyapp.common.Constants
import com.hema.cryptocurrencyapp.common.Resource
import com.hema.cryptocurrencyapp.domain.use_case.get_coin.GetCoinUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _stateMutableLiveData = MutableLiveData(CoinDetailState())
    val sateLiveData: LiveData<CoinDetailState> = _stateMutableLiveData

    init {
        savedStateHandle.get<String>(Constants.PARAM_COIN_ID)?.let { coinId ->
            getCoin(coinId)
        }
    }

    private fun getCoin(coinId: String) {
        getCoinUseCase(coinId).onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _stateMutableLiveData.value = CoinDetailState(isLoading = true)
                }
                is Resource.Success -> {
                    _stateMutableLiveData.value = CoinDetailState(coins = result.data)
                }
                is Resource.Error -> {
                    _stateMutableLiveData.value =
                        CoinDetailState(error = result.message ?: "Un Expected Error")

                }
            }

        }.launchIn(viewModelScope)
    }

}