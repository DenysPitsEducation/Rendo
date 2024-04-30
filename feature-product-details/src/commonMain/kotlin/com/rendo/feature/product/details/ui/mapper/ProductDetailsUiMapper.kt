package com.rendo.feature.product.details.ui.mapper

import com.raedghazal.kotlinx_datetime_ext.LocalDateTimeFormatter
import com.raedghazal.kotlinx_datetime_ext.Locale
import com.rendo.core.utils.formatPrice
import com.rendo.feature.product.details.domain.model.OwnerDomainModel
import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel
import com.rendo.feature.product.details.ui.model.OwnerUiModel
import com.rendo.feature.product.details.ui.model.ProductDetailsUiModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.byUnicodePattern

class ProductDetailsUiMapper {
    fun mapToUiModel(model: ProductDetailsDomainModel): ProductDetailsUiModel = model.run {
        val formatter = LocalDateTimeFormatter.ofPattern("dd MMMM", Locale.default())
        return ProductDetailsUiModel(
            id = id,
            name = name,
            description = description,
            imageUrls = imageUrls,
            price = "${price.formatPrice()}$currency / day",
            isInFavorites = isInFavorites,
            pickupDate = formatter.format(pickupDate),
            returnDate = formatter.format(returnDate),
            totalPrice = "${totalPrice.formatPrice()}$currency",
            ownerUiModel = owner.toUiModel(),
        )
    }

    private fun OwnerDomainModel.toUiModel(): OwnerUiModel {
        return OwnerUiModel(imageUrl = imageUrl, name = name)
    }
}