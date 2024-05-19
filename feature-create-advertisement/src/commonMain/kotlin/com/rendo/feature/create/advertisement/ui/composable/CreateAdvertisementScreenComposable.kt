package com.rendo.feature.create.advertisement.ui.composable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.core.utils.LabelLaunchedEffect
import com.rendo.feature.create.advertisement.domain.mvi.CreateAdvertisementLabel
import com.rendo.feature.create.advertisement.ui.CreateAdvertisementScreenModel
import com.rendo.feature.create.advertisement.ui.OnUserInteraction
import com.rendo.feature.create.advertisement.ui.mapper.CreateAdvertisementUiMapper
import com.rendo.feature.create.advertisement.ui.model.CreateAdvertisementUiModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import rendo.feature_create_advertisement.generated.resources.Res
import rendo.feature_create_advertisement.generated.resources.successful_creation_confirm_button
import rendo.feature_create_advertisement.generated.resources.successful_creation_description
import rendo.feature_create_advertisement.generated.resources.successful_creation_title

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
internal fun CreateAdvertisementScreenComposable(screenModel: CreateAdvertisementScreenModel) {
    val state by screenModel.store.stateFlow.collectAsState()
    val mapper: CreateAdvertisementUiMapper = koinInject()
    val uiModel = mapper.mapToUiModel(state)
    var showAlertDialog by remember { mutableStateOf(false) }
    val onUserInteraction: OnUserInteraction = { screenModel.store.accept(it) }

    when (uiModel) {
        is CreateAdvertisementUiModel.AuthorizationRequirement -> {
            AuthorizationRequirementComposable()
        }
        is CreateAdvertisementUiModel.Content -> {
            CreateAdvertisementContentComposable(uiModel, onUserInteraction)
        }
    }
    if (showAlertDialog) {
        AlertDialog(
            title = {
                Text(stringResource(Res.string.successful_creation_title))
            },
            text = {
                Text(stringResource(Res.string.successful_creation_description))
            },
            onDismissRequest = {
                showAlertDialog = false
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        showAlertDialog = false
                    }
                ) {
                    Text(stringResource(Res.string.successful_creation_confirm_button))
                }
            },
        )
    }

    LabelLaunchedEffect(screenModel.store.labels) {
        when (it) {
            is CreateAdvertisementLabel.ShowSuccessfulCreationDialog -> {
                showAlertDialog = true
            }
        }
    }
}
