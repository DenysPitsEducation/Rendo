package com.rendo.feature.rents.domain.repository

import com.rendo.feature.rents.domain.model.RentDomainModel

internal interface RentsRepository {
    suspend fun getRents(): Result<List<RentDomainModel>>
    suspend fun acceptRent(rent: RentDomainModel): Result<Unit>
    suspend fun rejectRent(rent: RentDomainModel): Result<Unit>
    suspend fun cancelRent(rent: RentDomainModel): Result<Unit>
    suspend fun deleteRent(rent: RentDomainModel): Result<Unit>
}