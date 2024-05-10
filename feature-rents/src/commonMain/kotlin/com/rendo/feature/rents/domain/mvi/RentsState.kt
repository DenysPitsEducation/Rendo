package com.rendo.feature.rents.domain.mvi

import com.rendo.feature.rents.domain.model.RentDomainModel

internal data class RentsState(
    val rents: List<RentDomainModel>,
)
