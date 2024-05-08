package com.rendo.feature.profile.domain.usecase

import com.rendo.feature.profile.domain.model.UserDomainModel
import com.rendo.feature.profile.domain.repository.ProfileRepository

// TODO Pits: Delete?
internal class GetUserUseCase(
    private val profileRepository: ProfileRepository,
) {
    fun invoke(): UserDomainModel? {
        return profileRepository.getUser()
    }
}