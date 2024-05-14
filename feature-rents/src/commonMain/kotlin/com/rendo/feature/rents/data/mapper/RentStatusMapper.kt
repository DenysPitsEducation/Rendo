package com.rendo.feature.rents.data.mapper

import com.rendo.core.data.model.RentStatusConstants
import com.rendo.feature.rents.domain.model.RentDomainModel

internal class RentStatusMapper {

    fun mapToDomainModel(status: String): RentDomainModel.Status {
        return when (status) {
            RentStatusConstants.ACCEPTED -> RentDomainModel.Status.ACCEPTED
            RentStatusConstants.REJECTED -> RentDomainModel.Status.REJECTED
            RentStatusConstants.CANCELLED -> RentDomainModel.Status.CANCELLED
            else -> RentDomainModel.Status.WAITING_FOR_ACCEPTANCE
        }
    }

    fun mapToDataString(status: RentDomainModel.Status): String {
        return when (status) {
            RentDomainModel.Status.WAITING_FOR_ACCEPTANCE -> RentStatusConstants.WAITING_FOR_ACCEPTANCE
            RentDomainModel.Status.ACCEPTED -> RentStatusConstants.ACCEPTED
            RentDomainModel.Status.REJECTED -> RentStatusConstants.REJECTED
            RentDomainModel.Status.CANCELLED -> RentStatusConstants.CANCELLED
        }
    }
}