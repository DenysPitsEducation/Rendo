package com.rendo.feature.rents.domain.repository

import com.rendo.feature.rents.domain.model.RentDomainModel

internal interface RentsRepository {
    fun getRents(): List<RentDomainModel>
}