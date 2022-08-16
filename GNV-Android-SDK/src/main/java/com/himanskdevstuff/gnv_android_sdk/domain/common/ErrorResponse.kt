package com.himanskdevstuff.gnv_android_sdk.domain.common

import com.google.gson.annotations.SerializedName
import com.himanskdevstuff.gnv_android_sdk.domain.common.ErrorData

data class ErrorResponse(
    @SerializedName("code") val code: String,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: ErrorData
)