package com.rendo.feature.profile.domain.mvi

sealed class ProfileLabel {
    data object OpenSignInFlow : ProfileLabel()
}
