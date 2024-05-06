package com.rendo.feature.profile.domain.mvi

import com.rendo.feature.profile.domain.model.ProfileDomainModel

data class ProfileState(
    val profile: ProfileDomainModel?,
)
