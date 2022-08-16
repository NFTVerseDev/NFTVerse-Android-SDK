package com.himanskdevstuff.gnv_android_sdk.data.remote.dto.NtfList
import com.himanskdevstuff.gnv_android_sdk.domain.model.NftListEntity

data class NftListDto(
    val content: List<Content>,
    val empty: Boolean,
    val first: Boolean,
    val last: Boolean,
    val number: Int,
    val numberOfElements: Int,
    val pageable: Pageable,
    val size: Int,
    val sort: SortX,
    val totalElements: Int,
    val totalPages: Int
)

fun NftListDto.toNftList() : NftListEntity {
    return NftListEntity(
        content
    )
}