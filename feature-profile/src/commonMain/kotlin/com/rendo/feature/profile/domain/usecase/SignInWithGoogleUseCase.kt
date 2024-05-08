package com.rendo.feature.profile.domain.usecase

import com.rendo.feature.profile.domain.repository.ProfileRepository

internal class SignInWithGoogleUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend fun invoke(idToken: String) {
        return profileRepository.signIn(idToken)
    }
}