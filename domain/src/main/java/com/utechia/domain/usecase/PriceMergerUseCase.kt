package com.utechia.domain.usecase

import com.utechia.domain.model.Exchange
import com.utechia.domain.model.Price
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PriceMergerUseCase @Inject constructor() {

    operator fun invoke(exchangeList: List<Exchange>, priceList: List<Price>) {
        mergeData(exchangeList, priceList)
    }

    private fun mergeData(exchangeList: List<Exchange>, priceList: List<Price>) {

        for (exchange in exchangeList) {
            priceList.find { it.name == exchange.name }?.let {
                exchange.buyPrice = it.buyPrice
                exchange.sellPrice = it.sellPrice
            }
        }
    }
}