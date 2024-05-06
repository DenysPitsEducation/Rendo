package com.rendo.feature.profile.data.repository

import com.rendo.feature.profile.domain.model.ProfileDomainModel
import com.rendo.core.domain.model.UiMode
import com.rendo.feature.profile.domain.repository.ProfileRepository

class ProfileRepositoryImpl : ProfileRepository {
    override fun getProfile(): ProfileDomainModel {
        return ProfileDomainModel(
            id = 1,
            name = "Denys",
            imageUrl = "https://picsum.photos/200?random=10",
        )
    }

    override fun getUiMode(): UiMode {
        return UiMode.DARK
    }
}