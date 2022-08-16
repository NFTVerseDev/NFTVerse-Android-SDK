package com.himanskdevstuff.gnv_android_sdk.data.remote.dto.NtfList

data class Content(
    val blockchain: String,
    val blockchainNftId: String,
    val marketplaceId: Int,
    val metaData: String,
    val nftId: Int,
    val status: String,
    val transactionId: String,
    val userId: Int
)