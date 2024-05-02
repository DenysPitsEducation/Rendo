package com.rendo.feature.product.details.ui.model

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates

@OptIn(ExperimentalMaterial3Api::class)
data class DatePickerUiModel(
    val pickupDateMillis: Long,
    val returnDateMillis: Long,
    val selectableDates: SelectableDates,
)
