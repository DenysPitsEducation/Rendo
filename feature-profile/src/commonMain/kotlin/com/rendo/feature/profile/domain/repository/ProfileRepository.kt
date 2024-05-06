package com.rendo.feature.profile.domain.repository

import com.rendo.feature.profile.domain.model.ProfileDomainModel
import com.rendo.core.domain.model.UiMode

interface ProfileRepository {
    fun getProfile(): ProfileDomainModel
    fun getUiMode(): UiMode
}