package com.rendo.feature.profile.domain.mvi

sealed class ProfileIntent {
    data class UiModeButtonClicked(val isDarkModeNow: Boolean) : ProfileIntent()
    data class GoogleTokenReceived(val idToken: String) : ProfileIntent()
    data object SignInButtonClicked : ProfileIntent()
    data object SignOutButtonClicked : ProfileIntent()
}
