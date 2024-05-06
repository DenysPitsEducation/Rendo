package com.rendo.core.domain.usecase

import com.rendo.core.domain.model.UiMode
import com.rendo.core.domain.repository.ThemeRepository

class SaveUiModeUseCase(
    private val themeRepository: ThemeRepository,
) {
    suspend fun invoke(uiMode: UiMode) {
        return themeRepository.saveUiMode(uiMode)
    }
}