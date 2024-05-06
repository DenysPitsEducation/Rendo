package com.rendo.feature.profile.domain.mvi

import com.rendo.feature.profile.domain.model.ProfileDomainModel

sealed class ProfileMessage {
    data class ProfileUpdated(val profile: ProfileDomainModel) : ProfileMessage()
}
