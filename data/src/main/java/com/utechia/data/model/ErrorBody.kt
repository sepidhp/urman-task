package com.utechia.data.model

import android.annotation.SuppressLint
import android.os.Parcelable
import com.utechia.data.base.ResponseObject
import com.utechia.domain.model.ErrorMessage
import com.utechia.domain.util.HttpErrors
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class ErrorBody(
    val message: String?,
    val status: Int?,
) : Parcelable, ResponseObject<ErrorMessage> {
    override fun toDomain(): ErrorMessage {
        return ErrorMessage(
            message = message,
            status = when (status) {
                401 -> HttpErrors.Unauthorized
                403 -> HttpErrors.Forbidden
                400 -> HttpErrors.BadRequest
                500 -> HttpErrors.ServerError
                409 -> HttpErrors.Conflict
                else -> HttpErrors.NotDefined
            }
        )
    }
}