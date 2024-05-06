package com.rendo.feature.profile.domain.mvi

sealed class ProfileIntent {
    data class UiModeButtonClicked(val isDarkModeNow: Boolean) : ProfileIntent()
    data object SignInButtonClicked : ProfileIntent()
    data object SignUpButtonClicked : ProfileIntent()
    data object SignOutButtonClicked : ProfileIntent()
}
