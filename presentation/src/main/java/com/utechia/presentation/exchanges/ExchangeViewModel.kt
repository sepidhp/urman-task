package com.utechia.presentation.exchanges

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utechia.domain.model.Exchange
import com.utechia.domain.socket.Event
import com.utechia.domain.socket.SocketHandler
import com.utechia.domain.usecase.GetExchangesUseCase
import com.utechia.domain.usecase.GetTokenUseCase
import com.utechia.domain.usecase.PriceMapperUseCase
import com.utechia.domain.usecase.PriceMergerUseCase
import com.utechia.domain.util.Constants
import com.utechia.domain.util.Result
import com.utechia.presentation.base.BaseViewModel
import com.utechia.presentation.util.DispatchersProvider
import com.utechia.presentation.util.TokenProvider
import com.utechia.presentation.util.UniqueIdProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val dispatchers: DispatchersProvider,
    private val tokenProvider: TokenProvider,
    private val uniqueIdProvider: UniqueIdProvider,
    private val getExchangesUseCase: GetExchangesUseCase,
    private val getTokenUseCase: GetTokenUseCase,
    private val socketHandler: SocketHandler,
    private val priceMapperUseCase: PriceMapperUseCase,
    private val priceMergerUseCase: PriceMergerUseCase
) : BaseViewModel(dispatchers) {
    private val _token: MutableLiveData<Result<String>> = MutableLiveData()
    private val _socketException: MutableLiveData<Exception> = MutableLiveData()
    private val _exchangeNames: MutableLiveData<Result<List<Exchange>>> = MutableLiveData()
    private val _exchanges: MutableLiveData<List<Exchange>> = MutableLiveData()

    init {
        fetchExchanges()
    }

    fun fetchGuestToken() {
        execute {
            _token.postValue(Result.Loading(true))
            when (val result = getTokenUseCase(
                Constants.EMPLOYEE,
                uniqueIdProvider.getUniqueId()
            )) {
                is Result.Success -> {
                    tokenProvider.persistToken(result.data)
                    fetchExchanges()
                }

                is Result.Error -> {
                    _token.postValue(result)
                }

                else -> {}
            }
        }
    }

    fun fetchExchanges() {
        if (tokenProvider.getToken().isNullOrEmpty()) {
            fetchGuestToken()
            return
        }
        execute {
            _exchangeNames.postValue(Result.Loading(true))
            when (val result = getExchangesUseCase()) {
                is Result.Success -> {
                    _exchangeNames.postValue(result)
                }

                is Result.Error -> {
                    _exchangeNames.postValue(result)
                }

                else -> {}
            }
        }
    }

    fun connectSocket() {
        socketHandler.connect(tokenProvider.getToken()!!) { exception ->
            _socketException.postValue(exception)
        }
    }

    fun disconnectSocket() {
        socketHandler.disconnect()
    }

    fun listenPriceEvent(exchangeList: List<Exchange>) {

        socketHandler.listenEvent(Event.PRICE) { data: Any ->

            val prices = priceMapperUseCase(data)

            prices.exchangePrices.nadirdoviz?.let {
                priceMergerUseCase(exchangeList, it.asList())
                _exchanges.postValue(exchangeList)
            }
        }
    }

    fun getToken(): LiveData<Result<String>> = _token
    fun getSocketException(): LiveData<Exception> = _socketException
    fun getExchangeNames(): LiveData<Result<List<Exchange>>> = _exchangeNames
    fun getExchanges(): LiveData<List<Exchange>> = _exchanges
}