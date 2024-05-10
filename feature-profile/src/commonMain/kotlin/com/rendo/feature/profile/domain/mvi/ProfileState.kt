package com.rendo.feature.profile.domain.mvi

import com.rendo.feature.profile.domain.model.UserDomainModel

internal data class ProfileState(
    val user: UserDomainModel?,
)
