package com.rendo.feature.rents.domain.mvi

import com.rendo.feature.rents.domain.model.DropdownAction

sealed class RentsIntent {
    data class RentClicked(val id: Long) : RentsIntent()
    data class AcceptButtonClicked(val id: Long) : RentsIntent()
    data class RejectButtonClicked(val id: Long) : RentsIntent()
    data class DialNumberButtonClicked(val id: Long) : RentsIntent()
    data class DropdownItemClicked(val id: Long, val action: DropdownAction) : RentsIntent()
}
