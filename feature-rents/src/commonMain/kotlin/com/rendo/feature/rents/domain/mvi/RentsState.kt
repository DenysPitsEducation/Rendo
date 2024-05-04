package com.rendo.feature.rents.domain.mvi

import com.rendo.feature.rents.domain.model.RentDomainModel

data class RentsState(
    val rents: List<RentDomainModel>,
)
