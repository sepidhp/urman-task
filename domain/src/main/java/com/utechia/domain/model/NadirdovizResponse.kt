package com.utechia.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.utechia.domain.util.ExchangesName

data class NadirdovizResponse(
    @SerializedName("usd_try")
    val usdTry: PriceItemRemote,
    @SerializedName("eur_try")
    val eurTry: PriceItemRemote,
    @SerializedName("eur_usd")
    val eurUsd: PriceItemRemote,
    @SerializedName("gold_ons")
    val goldOns: PriceItemRemote,
    @SerializedName("gold_try")
    val goldTry: PriceItemRemote,
    @SerializedName("gold_kg_usd")
    val goldKgUsd: PriceItemRemote,
    @SerializedName("gold_kg_eur")
    val goldKgEur: PriceItemRemote,
    @SerializedName("silver_ons")
    val silverOns: PriceItemRemote,
    @SerializedName("silver_try")
    val silverTry: PriceItemRemote,
    @SerializedName("old_quarter")
    val oldQuarter: PriceItemRemote,
    @SerializedName("new_quarter")
    val newQuarter: PriceItemRemote,
    @SerializedName("old_half")
    val oldHalf: PriceItemRemote,
    @SerializedName("new_half")
    val newHalf: PriceItemRemote,
    @SerializedName("old_complete")
    val oldComplete: PriceItemRemote,
    @SerializedName("new_complete")
    val newComplete: PriceItemRemote,
    @SerializedName("old_ata")
    val oldAta: PriceItemRemote,
    @SerializedName("new_ata")
    val newAta: PriceItemRemote,
    @SerializedName("old_gremese")
    val oldGremese: PriceItemRemote,
    @SerializedName("new_gremese")
    val newGremese: PriceItemRemote,
    @SerializedName("old_ata_5")
    val oldAta5: PriceItemRemote,
    @SerializedName("new_ata_5")
    val newAta5: PriceItemRemote,
)  {
    fun asList(): List<Price> = mutableListOf(
        Price(
            ExchangesName.USD_TRY.name,
            usdTry.buy,
            usdTry.sell
        ),
        Price(
            ExchangesName.EUR_TRY.name,
            eurTry.buy,
            eurTry.sell
        ),
        Price(
            ExchangesName.EUR_USD.name,
            eurUsd.buy,
            eurUsd.sell
        ),
        Price(
            ExchangesName.GOLD_ONS.name,
            goldOns.buy,
            goldOns.sell
        ),
        Price(
            ExchangesName.GOLD_TRY.name,
            goldTry.buy,
            goldTry.sell
        ),
        Price(
            ExchangesName.GOLD_KG_USD.name,
            goldKgUsd.buy,
            goldKgUsd.sell
        ),
        Price(
            ExchangesName.GOLD_KG_EUR.name,
            goldKgUsd.buy,
            goldKgUsd.sell
        ),
        Price(
            ExchangesName.SILVER_ONS.name,
            silverOns.buy,
            silverOns.sell
        ),
        Price(
            ExchangesName.SILVER_TRY.name,
            silverTry.buy,
            silverTry.sell
        ),
        Price(
            ExchangesName.OLD_QUARTER.name,
            oldQuarter.buy,
            oldQuarter.sell
        ),
        Price(
            ExchangesName.NEW_QUARTER.name,
            newQuarter.buy,
            newQuarter.sell
        ),
        Price(
            ExchangesName.OLD_HALF.name,
            oldHalf.buy,
            oldHalf.sell
        ),
        Price(
            ExchangesName.NEW_HALF.name,
            newHalf.buy,
            newHalf.sell
        ),
        Price(
            ExchangesName.OLD_COMPLETE.name,
            oldComplete.buy,
            oldComplete.sell
        ),
        Price(
            ExchangesName.NEW_COMPLETE.name,
            newComplete.buy,
            newComplete.sell
        ),
        Price(
            ExchangesName.OLD_ATA.name,
            oldAta.buy,
            oldAta.sell
        ),
        Price(
            ExchangesName.NEW_ATA.name,
            newAta.buy,
            newAta.sell
        ),
        Price(
            ExchangesName.OLD_GREMESE.name,
            oldGremese.buy,
            oldGremese.sell
        ),
        Price(
            ExchangesName.NEW_GREMESE.name,
            newGremese.buy,
            newGremese.sell
        ),
        Price(
            ExchangesName.OLD_ATA_5.name,
            oldAta5.buy,
            oldAta5.sell
        ),
        Price(
            ExchangesName.NEW_ATA_5.name,
            newAta5.buy,
            newAta5.sell
        )
    )
}
