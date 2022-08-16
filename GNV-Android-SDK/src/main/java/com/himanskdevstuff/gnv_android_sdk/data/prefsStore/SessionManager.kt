package com.himanskdevstuff.gnv_android_sdk.data.prefsStore

import android.content.SharedPreferences
import javax.inject.Inject

class SessionManager @Inject constructor(private val preferences: SharedPreferences) {

    private val AUTH_TOKEN_KEY = "AUTH_TOKEN_KEY"

     var xAuthToken: String = ""

    fun getAuthToken() = preferences.getString(AUTH_TOKEN_KEY, xAuthToken)

    fun setAuthToken(authToken: String) {
        val editor = preferences.edit()
        editor.putString(AUTH_TOKEN_KEY, authToken)
        editor.apply()
    }
}