package com.rendo.feature.profile.domain.mvi

internal sealed class ProfileLabel {
    data object OpenAdvertisements : ProfileLabel()
    data object OpenSignInFlow : ProfileLabel()
}
