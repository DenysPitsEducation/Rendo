package com.rendo.feature.profile.domain.usecase

import com.rendo.feature.profile.domain.model.GoogleToken
import com.rendo.feature.profile.domain.repository.ProfileRepository

internal class SignInWithGoogleUseCase(
    private val profileRepository: ProfileRepository,
) {
    suspend fun invoke(token: GoogleToken) {
        return profileRepository.signIn(token)
    }
}