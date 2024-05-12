package com.rendo.feature.product.details.data.mapper

import com.raedghazal.kotlinx_datetime_ext.LocalDateTimeFormatter
import com.raedghazal.kotlinx_datetime_ext.Locale
import com.raedghazal.kotlinx_datetime_ext.now
import com.rendo.core.data.model.ProductDetailsDataModel
import com.rendo.feature.product.details.domain.model.OwnerDomainModel
import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus

internal class ProductDetailsDomainMapper {

    fun mapToDomainModel(model: ProductDetailsDataModel, id: String): ProductDetailsDomainModel = model.run {
        val formatter = LocalDateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.default())
        val prohibitedDates = model.prohibitedDates.map {
            formatter.parseToLocalDate(it)
        }
        val firstAllowedDate = getFirstAllowedDate(prohibitedDates)
        return ProductDetailsDomainModel(
            id = id,
            name = name,
            description = description,
            imageUrls = imageUrls,
            price = price,
            currency = currency,
            isInFavorites = false,
            pickupDate = firstAllowedDate,
            returnDate = firstAllowedDate,
            prohibitedDates = prohibitedDates,
            totalPrice = price,
            owner = OwnerDomainModel(
                id = owner.id,
                name = owner.name,
                phone = owner.phone,
                imageUrl = owner.imageUrl,
            )
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