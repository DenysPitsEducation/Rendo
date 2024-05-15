package com.rendo.feature.product.details.data.mapper

import com.raedghazal.kotlinx_datetime_ext.LocalDateTimeFormatter
import com.raedghazal.kotlinx_datetime_ext.Locale
import com.rendo.core.data.model.RentDataModel
import com.rendo.core.data.model.RentStatusConstants
import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel
import dev.gitlive.firebase.auth.FirebaseUser

internal class RentDataMapper {

    fun mapToRentDataModel(productDetails: ProductDetailsDomainModel, tenantPhoneNumber: String, user: FirebaseUser) = productDetails.run {
        val formatter = LocalDateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.default())
        RentDataModel(
            productId = id,
            tenantId = user.uid,
            tenantPhoneNumber = tenantPhoneNumber,
            ownerId = owner.id,
            ownerPhoneNumber = owner.phone,
            imageUrl = imageUrls.firstOrNull(),
            name = name,
            status = RentStatusConstants.WAITING_FOR_ACCEPTANCE,
            pickupDate = formatter.format(pickupDate),
            returnDate = formatter.format(returnDate),
            price = totalPrice,
            currency = currency,
        )
    }
}