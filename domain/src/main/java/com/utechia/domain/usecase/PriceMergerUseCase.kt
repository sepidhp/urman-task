package com.utechia.domain.usecase

import com.utechia.domain.model.Exchange
import com.utechia.domain.model.ExchangeName
import com.utechia.domain.model.Price
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PriceMergerUseCase @Inject constructor() {

    operator fun invoke(
        exchangeNameList: List<ExchangeName>,
        priceList: List<Price>
    ): List<Exchange> {
        return mergeData(exchangeNameList, priceList)
    }

    private fun mergeData(
        exchangeNameList: List<ExchangeName>,
        priceList: List<Price>
    ): List<Exchange> {
        val exchanges = mutableListOf<Exchange>()
        for (exchangeName in exchangeNameList) {
            priceList.find { it.name == exchangeName.name }?.let {
                exchanges.add(
                    Exchange(
                        exchangeName.value,
                        exchangeName.sortId,
                        exchangeName.name,
                        it.buyPrice,
                        it.sellPrice
                    )
                )
            }
        }
        return exchanges
    }
}