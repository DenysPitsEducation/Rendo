package com.rendo.feature.product.details.ui.model

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates

@OptIn(ExperimentalMaterial3Api::class)
internal data class DatePickerUiModel(
    val initialPickupDateMillis: Long,
    val initialReturnDateMillis: Long,
    val selectableDates: SelectableDates,
    val isButtonEnabled: Boolean,
)
