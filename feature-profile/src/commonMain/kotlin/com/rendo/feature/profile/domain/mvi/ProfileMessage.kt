package com.rendo.feature.profile.domain.mvi

import com.rendo.feature.profile.domain.model.UserDomainModel

sealed class ProfileMessage {
    data class UserUpdated(val user: UserDomainModel?) : ProfileMessage()
}
