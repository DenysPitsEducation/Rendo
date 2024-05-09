package com.rendo.feature.profile.domain.mvi

import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.rendo.core.domain.model.UiMode
import com.rendo.core.domain.usecase.SaveUiModeUseCase
import com.rendo.feature.profile.domain.usecase.GetUserUseCase
import com.rendo.feature.profile.domain.usecase.SignInWithGoogleUseCase
import com.rendo.feature.profile.domain.usecase.SignOutUseCase
import kotlinx.coroutines.launch

internal class ProfileExecutor(
    private val getUserUseCase: GetUserUseCase,
    private val signInUseCase: SignInWithGoogleUseCase,
    private val signOutUseCase: SignOutUseCase,
    private val saveUiModeUseCase: SaveUiModeUseCase,
) : CoroutineExecutor<ProfileIntent, ProfileAction, ProfileState, ProfileMessage, ProfileLabel>() {

    override fun executeAction(action: ProfileAction) = when (action) {
        is ProfileAction.Init -> onInit()
        is ProfileAction.UserUpdated -> onUserUpdated(action)
    }

    private fun onInit() {

    }

    private fun onUserUpdated(action: ProfileAction.UserUpdated) {
        dispatch(ProfileMessage.UserUpdated(action.user))
    }

    override fun executeIntent(intent: ProfileIntent) = when (intent) {
        is ProfileIntent.UiModeButtonClicked -> onUiModeButtonClicked(intent)
        is ProfileIntent.GoogleTokenReceived -> onGoogleTokenReceived(intent)
        is ProfileIntent.SignInButtonClicked -> onSignInButtonClicked()
        is ProfileIntent.SignOutButtonClicked -> onSignOutButtonClicked()
    }

    private fun onGoogleTokenReceived(intent: ProfileIntent.GoogleTokenReceived) {
        scope.launch {
            signInUseCase.invoke(intent.token)
        }
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
        publish(ProfileLabel.OpenSignInFlow)
    }

    private fun onSignOutButtonClicked() {
        scope.launch {
            signOutUseCase.invoke()
        }
    }
}
