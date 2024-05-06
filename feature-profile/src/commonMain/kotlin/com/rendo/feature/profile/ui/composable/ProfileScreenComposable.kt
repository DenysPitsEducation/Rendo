package com.rendo.feature.profile.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.core.utils.LabelLaunchedEffect
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
    val onUserInteraction: OnUserInteraction = { screenModel.store.accept(it) }

    ProfileContentComposable(uiModel, onUserInteraction)

    LabelLaunchedEffect(screenModel.store.labels) { label ->
        /*when (label) {
            is ProfileLabel.Example -> {

            }
        }*/
    }
}
