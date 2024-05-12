package com.rendo.feature.create.advertisement.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.core.utils.LabelLaunchedEffect
import com.rendo.feature.create.advertisement.domain.mvi.CreateAdvertisementLabel
import com.rendo.feature.create.advertisement.ui.CreateAdvertisementScreenModel
import com.rendo.feature.create.advertisement.ui.OnUserInteraction
import com.rendo.feature.create.advertisement.ui.mapper.CreateAdvertisementUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
internal fun CreateAdvertisementScreenComposable(screenModel: CreateAdvertisementScreenModel) {
    val state by screenModel.store.stateFlow.collectAsState()
    val mapper: CreateAdvertisementUiMapper = koinInject()
    val uiModel = mapper.mapToUiModel(state)
    val onUserInteraction: OnUserInteraction = { screenModel.store.accept(it) }

    CreateAdvertisementContentComposable(uiModel, onUserInteraction)

    LabelLaunchedEffect(screenModel.store.labels) {
        when (it) {
            is CreateAdvertisementLabel.ShowSuccessfulCreationDialog -> {
                // TODO Pits:
            }
        }
    }
}
