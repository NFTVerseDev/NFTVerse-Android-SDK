package com.himanskdevstuff.gnv_android_sdk.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.himanskdevstuff.gnv_android_sdk.data.remote.dto.NtfList.NftListDto
import com.himanskdevstuff.gnv_android_sdk.domain.model.NftItem

interface GoNftVerseRepository {

    suspend fun fetchNftList(token : String, marketPlaceId : Int) : LiveData<PagingData<NftItem>>
}