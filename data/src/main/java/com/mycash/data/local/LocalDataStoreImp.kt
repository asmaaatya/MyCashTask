package com.mycash.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalDataStoreImp(
    private val dataStore: DataStore<Preferences>,
) : LocalDataStore {


    override suspend fun saveToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = accessToken
        }
    }

    override suspend fun saveLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE] = when (language) {
                "Arabic" -> "ar"
                else -> "en"
            }
        }
    }

    override suspend fun requestToken(): Flow<String?> {
        return dataStore.data.map { preferences -> preferences[TOKEN] }
    }

    override suspend fun requestLanguage(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[LANGUAGE] ?: "en"
        }
    }

    override suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN)
        }
    }

    companion object {
        private val TOKEN = stringPreferencesKey("key_token")
        private val LANGUAGE = stringPreferencesKey("key_language")
    }
}