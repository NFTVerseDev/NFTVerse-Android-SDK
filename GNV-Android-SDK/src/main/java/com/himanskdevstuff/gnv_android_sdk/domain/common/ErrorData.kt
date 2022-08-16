package com.himanskdevstuff.gnv_android_sdk.domain.common

import com.google.gson.annotations.SerializedName

data class ErrorData(@SerializedName("status") val status: String) {
}