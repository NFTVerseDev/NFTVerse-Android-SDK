package com.himanskdevstuff.gnv_android_sdk.data.remote.dto.NtfList

data class Pageable(
    val offset: Int,
    val pageNumber: Int,
    val pageSize: Int,
    val paged: Boolean,
    val sort: SortX,
    val unpaged: Boolean
)