package com.utechia.presentation.exchanges

import android.content.SharedPreferences
import android.provider.Settings
import androidx.lifecycle.MutableLiveData
import com.utechia.domain.model.Exchange
import dagger.hilt.android.lifecycle.HiltViewModel
import com.utechia.domain.usecase.GetExchangesUseCase
import com.utechia.domain.usecase.GetTokenUseCase
import com.utechia.domain.util.Constants
import com.utechia.presentation.base.BaseViewModel
import com.utechia.domain.util.Result
import com.utechia.presentation.util.DispatchersProvider
import java.net.Socket
import java.net.SocketImpl
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val dispatchers: DispatchersProvider,
    private val sharedPreferences: SharedPreferences,
    private val getExchangesUseCase: GetExchangesUseCase,
    private val getTokenUseCase: GetTokenUseCase
) : BaseViewModel(dispatchers) {
    private val _exchanges: MutableLiveData<Result<List<Exchange>>> = MutableLiveData()

    init {
        fetchExchanges()

    }

    fun fetchGuestToken() {
        execute {
            when (val result = getTokenUseCase(
                Constants.EMPLOYEE,
                sharedPreferences.getString(Constants.UNIQUE_ID, null)!!
            )) {
                is Result.Success -> {
                    sharedPreferences.edit().putString(Constants.TOKEN, result.data).apply()
                    fetchExchanges()
                }

                is Result.Error -> {}
                else -> {}
            }
        }
    }

    fun fetchExchanges() {
        if (sharedPreferences.getString(Constants.TOKEN, null).isNullOrEmpty()) {
            fetchGuestToken()
            return
        }
        execute {
            _exchanges.postValue(Result.Loading(true))
            when (val result = getExchangesUseCase()) {
                is Result.Success -> {
                    _exchanges.postValue(result)
                }

                is Result.Error -> {
                    _exchanges.postValue(result)
                }

                else -> {}
            }
        }
    }

    fun getExchanges(): MutableLiveData<Result<List<Exchange>>> = _exchanges
}