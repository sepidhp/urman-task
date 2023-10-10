package com.utechia.presentation.exchanges

import androidx.lifecycle.MutableLiveData
import com.utechia.domain.model.Exchange
import dagger.hilt.android.lifecycle.HiltViewModel
import com.utechia.domain.usecase.GetExchangesUseCase
import com.utechia.presentation.base.BaseViewModel
import com.utechia.domain.util.Result
import com.utechia.presentation.util.DispatchersProvider
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    private val dispatchers: DispatchersProvider,
    private val getExchangesUseCase: GetExchangesUseCase
) : BaseViewModel(dispatchers) {
    private val _exchanges: MutableLiveData<Result<List<Exchange>>> = MutableLiveData()

    init {
        fetchExchanges()
    }

    fun fetchExchanges() {
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