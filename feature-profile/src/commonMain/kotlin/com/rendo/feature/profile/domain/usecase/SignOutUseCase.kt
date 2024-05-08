package com.rendo.feature.profile.domain.usecase

import com.rendo.feature.profile.domain.repository.ProfileRepository

internal class SignOutUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend fun invoke() {
        return profileRepository.signOut()
    }
}