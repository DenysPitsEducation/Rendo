package com.rendo.feature.create.rent.ui.composable

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.core.utils.LabelLaunchedEffect
import com.rendo.feature.create.rent.domain.mvi.CreateRentLabel
import com.rendo.feature.create.rent.ui.CreateRentScreenModel
import com.rendo.feature.create.rent.ui.OnUserInteraction
import com.rendo.feature.create.rent.ui.mapper.CreateRentUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.compose.koinInject

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun CreateRentScreenComposable(screenModel: CreateRentScreenModel) {
    val state by screenModel.store.stateFlow.collectAsState()
    val mapper: CreateRentUiMapper = koinInject()
    val uiModel = mapper.mapToUiModel(state)
    val onUserInteraction: OnUserInteraction = { screenModel.store.accept(it) }

    CreateRentContentComposable(uiModel, onUserInteraction)

    LabelLaunchedEffect(screenModel.store.labels) {
        when (it) {
            is CreateRentLabel.ShowSuccessfulCreationDialog -> {
                // TODO Pits:
            }
        }
    }
}
