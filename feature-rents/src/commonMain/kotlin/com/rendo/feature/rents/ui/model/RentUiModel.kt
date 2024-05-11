package com.rendo.feature.rents.ui.model

internal data class RentUiModel(
    val id: String,
    val name: String,
    val imageUrl: String?,
    val status: StatusUiModel,
    val dates: String,
    val price: String,
    val showAcceptanceButtons: Boolean,
    val dropdownItems: List<DropdownItemUiModel>
)
