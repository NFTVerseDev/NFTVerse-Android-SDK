package com.test.data.prefsStore

import kotlinx.coroutines.flow.Flow

interface PrefsStore {

    fun getAuthToken(): Flow<String>

    suspend fun updateAuthToken(authToken : String)
}