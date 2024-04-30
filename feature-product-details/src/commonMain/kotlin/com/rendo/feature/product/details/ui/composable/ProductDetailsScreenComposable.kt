package com.rendo.feature.product.details.ui.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontVariation.weight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import com.rendo.feature.product.details.ui.ProductDetailsScreenModel
import com.rendo.feature.product.details.ui.mapper.ProductDetailsUiMapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import org.koin.compose.koinInject
import kotlin.time.Duration

@OptIn(ExperimentalCoroutinesApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreenComposable(screenModel: ProductDetailsScreenModel) {
    val stateFlow by screenModel.store.stateFlow.collectAsState()
    val productMapper: ProductDetailsUiMapper = koinInject()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showBottomSheet by remember { mutableStateOf(false) }
    val datePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = Clock.System.now().toEpochMilliseconds(),
        initialSelectedEndDateMillis = Clock.System.now()
            .plus(1, DateTimeUnit.DAY, TimeZone.currentSystemDefault()).toEpochMilliseconds()
    )
    val coroutineScope = rememberCoroutineScope()
    val uiModel = stateFlow.product?.let { productMapper.mapToUiModel(it) }
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
                IconButton(onClick = {}) {
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
                Modifier.padding(paddingValues),
                openBottomSheet = {
                    coroutineScope.launch {
                        showBottomSheet = true
                        sheetState.show()
                    }
                }
            )
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showBottomSheet = false },
                sheetState = sheetState,
            ) {
                Column {
                    // TODO Pits:
                    /*val selectableDates = object : SelectableDates {
                    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                        val instant = Instant.fromEpochMilliseconds(utcTimeMillis)
                        val dateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
                        return dateTime.dayOfMonth == 3
                    }
                }*/
                    DateRangePicker(
                        datePickerState,
                        showModeToggle = false,
                        modifier = Modifier.weight(1f)
                    )
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                sheetState.hide()
                                showBottomSheet = false
                            }
                        },
                        modifier = Modifier.padding(16.dp).fillMaxWidth()
                    ) {
                        Text("Choose these dates")
                    }
                }
            }
        }
    }
}
