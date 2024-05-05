package com.rendo.feature.create.rent.domain.usecase

import com.rendo.feature.create.rent.domain.model.RentDomainModel
import com.rendo.feature.create.rent.domain.repository.CreateRentRepository

internal class CreateRentUseCase(
    private val rentsRepository: CreateRentRepository,
) {
    fun invoke(rent: RentDomainModel) {
        return rentsRepository.createRent()
    }
}