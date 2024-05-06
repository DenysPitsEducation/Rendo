package com.rendo.core.domain.repository

import com.rendo.core.domain.model.UiMode
import kotlinx.coroutines.flow.Flow

interface ThemeRepository {
    fun getUiModeFlow(): Flow<UiMode>
    suspend fun saveUiMode(uiMode: UiMode)
}