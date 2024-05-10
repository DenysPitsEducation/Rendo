package com.rendo.feature.rents.domain.mvi

import com.rendo.feature.rents.domain.model.RentDomainModel

internal sealed class RentsMessage {
    data class RentsUpdated(val rents: List<RentDomainModel>) : RentsMessage()
    data class RentUpdated(val rent: RentDomainModel) : RentsMessage()
}
