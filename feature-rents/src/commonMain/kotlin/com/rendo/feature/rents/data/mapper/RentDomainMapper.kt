package com.rendo.feature.rents.data.mapper

import com.raedghazal.kotlinx_datetime_ext.LocalDateTimeFormatter
import com.raedghazal.kotlinx_datetime_ext.Locale
import com.rendo.core.data.model.RentDataModel
import com.rendo.feature.rents.domain.model.RentDomainModel

internal class RentDomainMapper(
    private val statusMapper: RentStatusMapper,
) {

    fun mapToDomainModel(model: RentDataModel, rentId: String, userId: String) = model.run {
        val type = if (userId == tenantId) {
            RentDomainModel.Type.RENT_IN
        } else {
            RentDomainModel.Type.RENT_OUT
        }
        val dateFormatter = LocalDateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.default())
        RentDomainModel(
            id = rentId,
            productId = productId,
            name = name,
            imageUrl = imageUrl,
            status = statusMapper.mapToDomainModel(status),
            pickupDate = dateFormatter.parseToLocalDate(pickupDate),
            returnDate = dateFormatter.parseToLocalDate(returnDate),
            price = price,
            currency = currency,
            phoneNumber = if (type == RentDomainModel.Type.RENT_IN) {
                ownerPhoneNumber
            } else {
                tenantPhoneNumber
            },
            type = type
        )
    }
}