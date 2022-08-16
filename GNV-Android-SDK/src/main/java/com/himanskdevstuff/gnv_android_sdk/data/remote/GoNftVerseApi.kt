package com.himanskdevstuff.gnv_android_sdk.data.remote

import com.himanskdevstuff.gnv_android_sdk.data.remote.dto.NtfList.NftListDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface GoNftVerseApi {
    @GET("v1/flow/marketplace/{marketPlaceId}/nft/list")
    suspend fun fetchNftList(
        @Path("marketPlaceId") marketPlaceId : Int,
        @Query("page") page : Int,
        @Header("x-app-token") token : String
    ) : Response<NftListDto>
}