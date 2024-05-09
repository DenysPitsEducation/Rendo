package com.rendo.feature.profile.domain.mvi

import com.rendo.feature.profile.domain.model.GoogleToken

sealed class ProfileIntent {
    data class UiModeButtonClicked(val isDarkModeNow: Boolean) : ProfileIntent()
    data class GoogleTokenReceived(val token: GoogleToken) : ProfileIntent()
    data object SignInButtonClicked : ProfileIntent()
    data object SignOutButtonClicked : ProfileIntent()
}
