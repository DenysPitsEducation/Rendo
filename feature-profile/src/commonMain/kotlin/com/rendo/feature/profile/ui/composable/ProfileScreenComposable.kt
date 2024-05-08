package com.rendo.feature.profile.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.core.theme.LocalSnackbarHostState
import com.rendo.core.utils.LabelLaunchedEffect
import com.rendo.feature.profile.domain.mvi.ProfileIntent
import com.rendo.feature.profile.domain.mvi.ProfileLabel
import com.rendo.feature.profile.ui.GoogleTokenProviderFactory
import com.rendo.feature.profile.ui.OnUserInteraction
import com.rendo.feature.profile.ui.ProfileScreenModel
import com.rendo.feature.profile.ui.mapper.ProfileUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun ProfileScreenComposable(screenModel: ProfileScreenModel) {
    val state by screenModel.store.stateFlow.collectAsState()
    val mapper: ProfileUiMapper = koinInject()
    val uiModel = mapper.mapToUiModel(state)
    val userProviderFactory: GoogleTokenProviderFactory = koinInject()
    val userProvider = userProviderFactory.create()
    val onUserInteraction: OnUserInteraction = { screenModel.store.accept(it) }
    val snackbarState = LocalSnackbarHostState.current

    ProfileContentComposable(uiModel, onUserInteraction)

    LabelLaunchedEffect(screenModel.store.labels) { label ->
        when (label) {
            ProfileLabel.OpenSignInFlow -> {
                val userResult = userProvider.provide()
                if (userResult.isSuccess) {
                    val idToken = userResult.getOrThrow()
                    onUserInteraction(ProfileIntent.GoogleTokenReceived(idToken))
                } else {
                    snackbarState.showSnackbar("Something went wrong during google sign in")
                }
            }
        }
    }
}
