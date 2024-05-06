package com.rendo.feature.profile.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.core.domain.model.UiMode
import com.rendo.core.domain.usecase.SaveUiModeUseCase
import com.rendo.feature.profile.domain.usecase.GetProfileUseCase
import kotlinx.coroutines.launch

internal class ProfileExecutor(
    private val getProfileUseCase: GetProfileUseCase,
    private val saveUiModeUseCase: SaveUiModeUseCase,
) : CoroutineExecutor<ProfileIntent, ProfileAction, ProfileState, ProfileMessage, ProfileLabel>() {

    override fun executeAction(action: ProfileAction) = when (action) {
        is ProfileAction.Init -> onInit()
    }

    private fun onInit() {
        scope.launch {
            val profile = getProfileUseCase.invoke()
            dispatch(ProfileMessage.ProfileUpdated(profile))
        }
    }

    override fun executeIntent(intent: ProfileIntent) = when (intent) {
        is ProfileIntent.UiModeButtonClicked -> onUiModeButtonClicked(intent)
        is ProfileIntent.SignInButtonClicked -> onSignInButtonClicked()
        is ProfileIntent.SignOutButtonClicked -> onSignOutButtonClicked()
        is ProfileIntent.SignUpButtonClicked -> onSignUpButtonClicked()
    }

    private fun onUiModeButtonClicked(intent: ProfileIntent.UiModeButtonClicked) {
        scope.launch {
            val uiModeUpdated = if (intent.isDarkModeNow) {
                UiMode.LIGHT
            } else {
                UiMode.DARK
            }
            saveUiModeUseCase.invoke(uiModeUpdated)
        }
    }

    private fun onSignInButtonClicked() {

    }

    private fun onSignOutButtonClicked() {

    }

    private fun onSignUpButtonClicked() {

    }
}
