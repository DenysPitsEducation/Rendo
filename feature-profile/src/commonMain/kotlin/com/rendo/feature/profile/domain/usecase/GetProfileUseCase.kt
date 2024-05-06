package com.rendo.feature.profile.domain.usecase

import com.rendo.feature.profile.domain.model.ProfileDomainModel
import com.rendo.feature.profile.domain.repository.ProfileRepository

internal class GetProfileUseCase(
    private val profileRepository: ProfileRepository,
) {
    fun invoke(): ProfileDomainModel {
        return profileRepository.getProfile()
    }
}