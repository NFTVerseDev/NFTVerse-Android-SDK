package com.test.data.prefsStore

import android.content.Context
import androidx.datastore.core.DataMigration
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.himanskdevstuff.gnv_android_sdk.di.PREFS_NAME
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val ELO_ELO_STORE_NAME = "elo_elo_data_store"

class PrefsStoreImpl @Inject constructor(
    @ApplicationContext context: Context
) : PrefsStore {

    private val Context.dataStore by preferencesDataStore(
        name = ELO_ELO_STORE_NAME,
        produceMigrations = { context ->
            listOf(SharedPreferencesMigration(context, PREFS_NAME))
        })

    private val dataStore = context.dataStore

    override fun getAuthToken() = dataStore.data.catch { exception ->
        if (exception is IOException) {
            emit(emptyPreferences())
        } else {
            throw exception
        }
    }.map { it[PreferencesKeys.AUTH_TOKEN_KEY] ?: "" }

    override suspend fun updateAuthToken(authToken: String) {
        dataStore.edit {
            it[PreferencesKeys.AUTH_TOKEN_KEY] = authToken
        }
    }

    private object PreferencesKeys {
        val AUTH_TOKEN_KEY = stringPreferencesKey("AUTH_TOKEN_KEY")
    }
}