package com.rendo.core.domain.usecase

import com.rendo.core.domain.model.UiMode
import com.rendo.core.domain.repository.ThemeRepository
import kotlinx.coroutines.flow.Flow

class GetUiModeFlowUseCase(
    private val themeRepository: ThemeRepository,
) {
    fun invoke(): Flow<UiMode> {
        return themeRepository.getUiModeFlow()
    }
}