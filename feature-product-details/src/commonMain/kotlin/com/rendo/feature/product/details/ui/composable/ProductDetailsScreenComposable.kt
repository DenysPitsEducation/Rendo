package com.rendo.feature.product.details.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.core.dial.Dialer
import com.rendo.core.utils.LabelLaunchedEffect
import com.rendo.feature.product.details.domain.mvi.ProductDetailsIntent
import com.rendo.feature.product.details.domain.mvi.ProductDetailsLabel
import com.rendo.feature.product.details.ui.ProductDetailsScreenModel
import com.rendo.feature.product.details.ui.mapper.ProductDetailsUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import rendo.feature_product_details.generated.resources.Res
import rendo.feature_product_details.generated.resources.date_picker_dialog_button
import rendo.feature_product_details.generated.resources.successful_rent_confirm_button
import rendo.feature_product_details.generated.resources.successful_rent_description
import rendo.feature_product_details.generated.resources.successful_rent_title

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun ProductDetailsScreenComposable(screenModel: ProductDetailsScreenModel) {
    val state by screenModel.store.stateFlow.collectAsState()
    val productMapper: ProductDetailsUiMapper = koinInject()
    val dialer: Dialer = koinInject()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }
    var showAlertDialog by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    val uiModel = productMapper.mapToUiModel(state)
    val onUserInteraction: OnUserInteraction = { screenModel.store.accept(it) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        topBar = {
            TopAppBar(title = {}, navigationIcon = {
                val navigator = LocalNavigator.current
                IconButton(onClick = { navigator?.pop() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            }, actions = {
                IconButton(onClick = { onUserInteraction(ProductDetailsIntent.FavoriteButtonClicked) }) {
                    Icon(
                        imageVector = if (uiModel?.isInFavorites == true) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = null,
                    )
                }
            })
        }) { paddingValues ->
        if (uiModel != null) {
            ProductDetailsContentComposable(
                uiModel,
                onUserInteraction,
                Modifier.padding(paddingValues),
            )
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                ) {
                    Column {
                        val datePickerState = rememberDateRangePickerState(
                            initialSelectedStartDateMillis = uiModel.datePickerUiModel.initialPickupDateMillis,
                            initialSelectedEndDateMillis = uiModel.datePickerUiModel.initialReturnDateMillis,
                            selectableDates = uiModel.datePickerUiModel.selectableDates,
                        )

                        LaunchedEffect(datePickerState.selectedStartDateMillis, datePickerState.selectedEndDateMillis) {
                            onUserInteraction(ProductDetailsIntent.DateRangeChanged(
                                pickupDateMillis = datePickerState.selectedStartDateMillis,
                                returnDateMillis = datePickerState.selectedEndDateMillis
                            ))
                        }

                        DateRangePicker(
                            datePickerState,
                            showModeToggle = false,
                            modifier = Modifier.weight(1f)
                        )
                        Button(
                            onClick = {
                                coroutineScope.launch {
                                    onUserInteraction(ProductDetailsIntent.ChooseDateDialogButtonClicked(
                                        pickupDateMillis = datePickerState.selectedStartDateMillis ?: return@launch,
                                        returnDateMillis = datePickerState.selectedEndDateMillis ?: return@launch
                                    ))
                                    sheetState.hide()
                                    showBottomSheet = false
                                }
                            },
                            enabled = uiModel.datePickerUiModel.isButtonEnabled,
                            modifier = Modifier.padding(16.dp).fillMaxWidth()
                        ) {
                            Text(stringResource(Res.string.date_picker_dialog_button))
                        }
                    }
                }
            }
            if (showAlertDialog) {
                AlertDialog(
                    title = {
                        Text(text = stringResource(Res.string.successful_rent_title))
                    },
                    text = {
                        Text(text = stringResource(Res.string.successful_rent_description))
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
                            Text(stringResource(Res.string.successful_rent_confirm_button))
                        }
                    },
                )
            }
        }
    }

    LabelLaunchedEffect(screenModel.store.labels) { label ->
        when (label) {
            is ProductDetailsLabel.OpenDateRangePicker -> {
                showBottomSheet = true
                sheetState.show()
            }

            is ProductDetailsLabel.DialNumber -> {
                dialer.makeCall(label.number)
            }

            is ProductDetailsLabel.ShowSuccessfulRentDialog -> {
                showAlertDialog = true
            }
        }
    }
}
