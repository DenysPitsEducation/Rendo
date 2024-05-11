package com.rendo.feature.profile.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.core.theme.LocalSnackbarHostState
import com.rendo.core.utils.LabelLaunchedEffect
import com.rendo.feature.profile.di.ProfileRouter
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
internal fun ProfileScreenComposable(screenModel: ProfileScreenModel) {
    val state by screenModel.store.stateFlow.collectAsState()
    val mapper: ProfileUiMapper = koinInject()
    val uiModel = mapper.mapToUiModel(state)
    val tokenProviderFactory: GoogleTokenProviderFactory = koinInject()
    val tokenProvider = tokenProviderFactory.create()
    val onUserInteraction: OnUserInteraction = { screenModel.store.accept(it) }
    val snackbarState = LocalSnackbarHostState.current
    val router: ProfileRouter = koinInject()
    val navigator = LocalNavigator.currentOrThrow

    ProfileContentComposable(uiModel, onUserInteraction)

    LabelLaunchedEffect(screenModel.store.labels) { label ->
        when (label) {
            ProfileLabel.OpenAdvertisements -> {
                router.navigateToAdvertisements(navigator)
            }

            ProfileLabel.OpenSignInFlow -> {
                val tokenResult = tokenProvider.provide()
                if (tokenResult.isSuccess) {
                    val token = tokenResult.getOrThrow()
                    onUserInteraction(ProfileIntent.GoogleTokenReceived(token))
                } else {
                    snackbarState.showSnackbar("Something went wrong during google sign in")
                }
            }
        }
    }
}
