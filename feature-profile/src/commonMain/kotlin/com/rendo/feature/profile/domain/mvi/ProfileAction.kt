package com.rendo.feature.profile.domain.mvi

import com.rendo.feature.profile.domain.model.UserDomainModel

internal sealed class ProfileAction {
    data object Init : ProfileAction()
    data class UserUpdated(val user: UserDomainModel?) : ProfileAction()
}
