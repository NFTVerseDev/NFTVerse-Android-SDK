package com.himanskdevstuff.gnv_android_sdk.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.himanskdevstuff.gnv_android_sdk.data.remote.GoNftVerseApi
import com.himanskdevstuff.gnv_android_sdk.data.prefsStore.SessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    private const val GO_NFT_VERSE_END_POINT = "http://blockchainservice-dev.nftverse.co.in/"

    @Singleton
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) as Interceptor


    @Singleton
    @Provides
    fun provideOkHttpClient(
        logInterceptor: Interceptor,
        @ApplicationContext appContext: Context,
        sessionManager: SessionManager
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            //if (BuildConfig.DEBUG) {
            connectTimeout(1, TimeUnit.MINUTES)
            readTimeout(5, TimeUnit.MINUTES)
            writeTimeout(5, TimeUnit.MINUTES)
            callTimeout(5, TimeUnit.MINUTES)
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    if (!sessionManager.getAuthToken().isNullOrEmpty()) {
                        sessionManager.getAuthToken()?.let { builder.header("x-app-token", it) }
                    } else {
                        builder.header("x-app-token", "E9FE46D9-FC53-480F-9DC6-D26A7DE233A0")
                    }

                    return@Interceptor chain.proceed(builder.build())
                }
            )
            addInterceptor(logInterceptor)
                .cache(Cache(appContext.cacheDir, (10 * 1024 * 1024).toLong()))
//                .callTimeout(20, TimeUnit.SECONDS)
            // }
        }.build()
    }

    @Singleton
    @Provides
    fun provideSessionManager(preferences: SharedPreferences) =
        SessionManager(preferences)


    @Qualifiers.GoNftVerseKey
    @Singleton
    @Provides
    fun provideGoNftVerseRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl(GO_NFT_VERSE_END_POINT)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()


    @Singleton
    @Provides
    fun provideGoNftVerseAPIService(@Qualifiers.GoNftVerseKey retrofit: Retrofit): GoNftVerseApi =
        retrofit.create(GoNftVerseApi::class.java)

}



