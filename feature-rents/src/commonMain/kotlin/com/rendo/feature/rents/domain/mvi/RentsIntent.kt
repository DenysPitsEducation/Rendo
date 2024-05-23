package com.rendo.feature.rents.domain.mvi

import com.rendo.feature.rents.domain.model.DropdownAction

internal sealed class RentsIntent {
    data object ScreenOpened : RentsIntent()
    data class RentClicked(val id: String) : RentsIntent()
    data class AcceptButtonClicked(val id: String) : RentsIntent()
    data class RejectButtonClicked(val id: String) : RentsIntent()
    data class DialNumberButtonClicked(val id: String) : RentsIntent()
    data class DropdownItemClicked(val id: String, val action: DropdownAction) : RentsIntent()
}
