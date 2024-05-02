package com.rendo.feature.product.details.data.repository

import com.raedghazal.kotlinx_datetime_ext.now
import com.rendo.feature.product.details.domain.model.OwnerDomainModel
import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel
import com.rendo.feature.product.details.domain.repository.ProductDetailsRepository
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.TimeZone
import kotlinx.datetime.plus

class ProductDetailsRepositoryImpl : ProductDetailsRepository {
    override fun getProductDetails(id: Long): ProductDetailsDomainModel {
        val prohibitedDates = listOf(
            LocalDate(2024, Month.MAY, 2),
            LocalDate(2024, Month.MAY, 5),
            LocalDate(2024, Month.MAY, 6),
        )
        val firstAllowedDate = getFirstAllowedDate(prohibitedDates)
        return ProductDetailsDomainModel(
            id = 1,
            name = "HyperDrive 3000",
            description = "It is the description of the HyperDrive 3000 product. It is awesome",
            imageUrls = listOf(
                "https://picsum.photos/600?random=1",
                "https://picsum.photos/600?random=2",
                "https://picsum.photos/600?random=3"
            ),
            price = 199.99,
            currency = "$",
            isInFavorites = true,
            owner = OwnerDomainModel(
                id = 1,
                name = "George",
                phone = "+380951234567",
                imageUrl = "https://picsum.photos/100?random=4",
            ),
            pickupDate = firstAllowedDate,
            returnDate = firstAllowedDate,
            prohibitedDates = prohibitedDates,
            totalPrice = 199.99,
        )
    }

    private fun getFirstAllowedDate(prohibitedDates: List<LocalDate>): LocalDate {
        var date = LocalDate.now()
        while (date in prohibitedDates) {
            date = date.plus(1, DateTimeUnit.DAY)
        }
        return date
    }
}