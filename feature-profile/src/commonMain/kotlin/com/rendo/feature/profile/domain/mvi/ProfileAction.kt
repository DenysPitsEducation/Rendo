package com.rendo.feature.profile.domain.mvi

sealed class ProfileAction {
    data object Init : ProfileAction()
}
