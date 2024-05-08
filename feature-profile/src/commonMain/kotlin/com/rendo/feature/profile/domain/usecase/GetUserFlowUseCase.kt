package com.rendo.feature.profile.domain.usecase

import com.rendo.feature.profile.domain.model.UserDomainModel
import com.rendo.feature.profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

internal class GetUserFlowUseCase(
    private val profileRepository: ProfileRepository,
) {
    fun invoke(): Flow<UserDomainModel?> {
        return profileRepository.getUserFlow()
    }
}