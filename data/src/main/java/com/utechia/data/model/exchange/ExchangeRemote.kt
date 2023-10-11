package com.utechia.data.model.exchange

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.utechia.data.base.ResponseObject
import com.utechia.domain.model.Exchange
import com.utechia.domain.util.ExchangesName

@Parcelize
data class ExchangeRemote(
    @SerializedName("usd_try")
    val usdTry: ExchangeItemRemote,
    @SerializedName("eur_try")
    val eurTry: ExchangeItemRemote,
    @SerializedName("eur_usd")
    val eurUsd: ExchangeItemRemote,
    @SerializedName("gold_ons")
    val goldOns: ExchangeItemRemote,
    @SerializedName("gold_try")
    val goldTry: ExchangeItemRemote,
    @SerializedName("gold_kg_usd")
    val goldKgUsd: ExchangeItemRemote,
    @SerializedName("gold_kg_eur")
    val goldKgEur: ExchangeItemRemote,
    @SerializedName("silver_ons")
    val silverOns: ExchangeItemRemote,
    @SerializedName("silver_try")
    val silverTry: ExchangeItemRemote,
    @SerializedName("old_quarter")
    val oldQuarter: ExchangeItemRemote,
    @SerializedName("new_quarter")
    val newQuarter: ExchangeItemRemote,
    @SerializedName("old_half")
    val oldHalf: ExchangeItemRemote,
    @SerializedName("new_half")
    val newHalf: ExchangeItemRemote,
    @SerializedName("old_complete")
    val oldComplete: ExchangeItemRemote,
    @SerializedName("new_complete")
    val newComplete: ExchangeItemRemote,
    @SerializedName("old_ata")
    val oldAta: ExchangeItemRemote,
    @SerializedName("new_ata")
    val newAta: ExchangeItemRemote,
    @SerializedName("old_gremese")
    val oldGremese: ExchangeItemRemote,
    @SerializedName("new_gremese")
    val newGremese: ExchangeItemRemote,
    @SerializedName("old_ata_5")
    val oldAta5: ExchangeItemRemote,
    @SerializedName("new_ata_5")
    val newAta5: ExchangeItemRemote,
) : Parcelable, ResponseObject<List<Exchange>> {
    override fun toDomain(): List<Exchange> = mutableListOf(
        Exchange(
            usdTry.value,
            usdTry.sortId,
            ExchangesName.USD_TRY.name,
            null,
            null
        ),
        Exchange(
            eurTry.value,
            eurTry.sortId,
            ExchangesName.EUR_TRY.name,
            null,
            null
        ),
        Exchange(
            eurUsd.value,
            eurUsd.sortId,
            ExchangesName.EUR_USD.name,
            null,
            null
        ),
        Exchange(
            goldOns.value,
            goldOns.sortId,
            ExchangesName.GOLD_ONS.name,
            null,
            null
        ),
        Exchange(
            goldTry.value,
            goldTry.sortId,
            ExchangesName.GOLD_TRY.name,
            null,
            null
        ),
        Exchange(
            goldKgUsd.value,
            goldKgUsd.sortId,
            ExchangesName.GOLD_KG_USD.name,
            null,
            null
        ),
        Exchange(
            goldKgEur.value,
            goldKgEur.sortId,
            ExchangesName.GOLD_KG_EUR.name,
            null,
            null
        ),
        Exchange(
            silverOns.value,
            silverOns.sortId,
            ExchangesName.SILVER_ONS.name,
            null,
            null
        ),
        Exchange(
            silverTry.value,
            silverTry.sortId,
            ExchangesName.SILVER_TRY.name,
            null,
            null
        ),
        Exchange(
            oldQuarter.value,
            oldQuarter.sortId,
            ExchangesName.OLD_QUARTER.name,
            null,
            null
        ),
        Exchange(
            newQuarter.value,
            newQuarter.sortId,
            ExchangesName.NEW_QUARTER.name,
            null,
            null
        ),
        Exchange(
            oldHalf.value,
            oldHalf.sortId,
            ExchangesName.OLD_HALF.name,
            null,
            null
        ),
        Exchange(
            newHalf.value,
            newHalf.sortId,
            ExchangesName.NEW_HALF.name,
            null,
            null
        ),
        Exchange(
            oldComplete.value,
            oldComplete.sortId,
            ExchangesName.OLD_COMPLETE.name,
            null,
            null
        ),
        Exchange(
            newComplete.value,
            newComplete.sortId,
            ExchangesName.NEW_COMPLETE.name,
            null,
            null
        ),
        Exchange(
            oldAta.value,
            oldAta.sortId,
            ExchangesName.OLD_ATA.name,
            null,
            null
        ),
        Exchange(
            newAta.value,
            newAta.sortId,
            ExchangesName.NEW_ATA.name,
            null,
            null
        ),
        Exchange(
            oldGremese.value,
            oldGremese.sortId,
            ExchangesName.OLD_GREMESE.name,
            null,
            null
        ),
        Exchange(
            newGremese.value,
            newGremese.sortId,
            ExchangesName.NEW_GREMESE.name,
            null,
            null
        ),
        Exchange(
            oldAta5.value,
            oldAta5.sortId,
            ExchangesName.OLD_ATA_5.name,
            null,
            null
        ),
        Exchange(
            newAta5.value,
            newAta5.sortId,
            ExchangesName.NEW_ATA_5.name,
            null,
            null
        )
    )
}