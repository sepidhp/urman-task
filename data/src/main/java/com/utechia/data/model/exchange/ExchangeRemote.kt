package com.utechia.data.model.exchange

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.utechia.data.base.ResponseObject
import com.utechia.domain.model.ExchangeName
import com.utechia.domain.util.ExchangesName
import kotlinx.parcelize.Parcelize

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
) : Parcelable, ResponseObject<List<ExchangeName>> {
    override fun toDomain(): List<ExchangeName> = mutableListOf(
        ExchangeName(
            usdTry.value,
            usdTry.sortId,
            ExchangesName.USD_TRY.name
        ),
        ExchangeName(
            eurTry.value,
            eurTry.sortId,
            ExchangesName.EUR_TRY.name
        ),
        ExchangeName(
            eurUsd.value,
            eurUsd.sortId,
            ExchangesName.EUR_USD.name
        ),
        ExchangeName(
            goldOns.value,
            goldOns.sortId,
            ExchangesName.GOLD_ONS.name
        ),
        ExchangeName(
            goldTry.value,
            goldTry.sortId,
            ExchangesName.GOLD_TRY.name
        ),
        ExchangeName(
            goldKgUsd.value,
            goldKgUsd.sortId,
            ExchangesName.GOLD_KG_USD.name
        ),
        ExchangeName(
            goldKgEur.value,
            goldKgEur.sortId,
            ExchangesName.GOLD_KG_EUR.name
        ),
        ExchangeName(
            silverOns.value,
            silverOns.sortId,
            ExchangesName.SILVER_ONS.name
        ),
        ExchangeName(
            silverTry.value,
            silverTry.sortId,
            ExchangesName.SILVER_TRY.name
        ),
        ExchangeName(
            oldQuarter.value,
            oldQuarter.sortId,
            ExchangesName.OLD_QUARTER.name
        ),
        ExchangeName(
            newQuarter.value,
            newQuarter.sortId,
            ExchangesName.NEW_QUARTER.name
        ),
        ExchangeName(
            oldHalf.value,
            oldHalf.sortId,
            ExchangesName.OLD_HALF.name
        ),
        ExchangeName(
            newHalf.value,
            newHalf.sortId,
            ExchangesName.NEW_HALF.name
        ),
        ExchangeName(
            oldComplete.value,
            oldComplete.sortId,
            ExchangesName.OLD_COMPLETE.name
        ),
        ExchangeName(
            newComplete.value,
            newComplete.sortId,
            ExchangesName.NEW_COMPLETE.name
        ),
        ExchangeName(
            oldAta.value,
            oldAta.sortId,
            ExchangesName.OLD_ATA.name
        ),
        ExchangeName(
            newAta.value,
            newAta.sortId,
            ExchangesName.NEW_ATA.name
        ),
        ExchangeName(
            oldGremese.value,
            oldGremese.sortId,
            ExchangesName.OLD_GREMESE.name
        ),
        ExchangeName(
            newGremese.value,
            newGremese.sortId,
            ExchangesName.NEW_GREMESE.name
        ),
        ExchangeName(
            oldAta5.value,
            oldAta5.sortId,
            ExchangesName.OLD_ATA_5.name
        ),
        ExchangeName(
            newAta5.value,
            newAta5.sortId,
            ExchangesName.NEW_ATA_5.name
        )
    )
}