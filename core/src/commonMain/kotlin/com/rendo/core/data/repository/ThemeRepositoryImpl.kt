package com.rendo.core.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.rendo.core.domain.model.UiMode
import com.rendo.core.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class ThemeRepositoryImpl(
    private val dataStore: DataStore<Preferences>
) : ThemeRepository {
    override fun getUiModeFlow(): Flow<UiMode> {
        return dataStore.data.map { preferences ->
            val isLightMode = preferences[booleanPreferencesKey(LIGHT_MODE)]
            when (isLightMode) {
                true -> UiMode.LIGHT
                false -> UiMode.DARK
                null -> UiMode.SYSTEM
            }
        }
    }

    override suspend fun saveUiMode(uiMode: UiMode) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(LIGHT_MODE)] = uiMode == UiMode.LIGHT
        }
    }

    private companion object {
        const val LIGHT_MODE = "light_mode"
    }
}