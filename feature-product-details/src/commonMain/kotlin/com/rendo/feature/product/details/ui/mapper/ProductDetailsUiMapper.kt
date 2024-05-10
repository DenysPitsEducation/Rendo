package com.rendo.feature.product.details.ui.mapper

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import com.raedghazal.kotlinx_datetime_ext.LocalDateTimeFormatter
import com.raedghazal.kotlinx_datetime_ext.Locale
import com.raedghazal.kotlinx_datetime_ext.atStartOfDay
import com.raedghazal.kotlinx_datetime_ext.now
import com.rendo.core.utils.formatPrice
import com.rendo.feature.product.details.domain.model.OwnerDomainModel
import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel
import com.rendo.feature.product.details.ui.model.DatePickerUiModel
import com.rendo.feature.product.details.ui.model.OwnerUiModel
import com.rendo.feature.product.details.ui.model.ProductDetailsUiModel
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime


internal class ProductDetailsUiMapper {
    @OptIn(ExperimentalMaterial3Api::class)
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
            datePickerUiModel = DatePickerUiModel(
                pickupDateMillis = pickupDate.atStartOfDay().toInstant(TimeZone.UTC).toEpochMilliseconds(),
                returnDateMillis = returnDate.atStartOfDay().toInstant(TimeZone.UTC).toEpochMilliseconds(),
                selectableDates = object : SelectableDates {
                    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                        val date: LocalDate = Instant.fromEpochMilliseconds(utcTimeMillis)
                            .toLocalDateTime(TimeZone.UTC).date
                        val nowDate = LocalDate.now(TimeZone.UTC)
                        return date >= nowDate && date !in model.prohibitedDates
                    }
                }
            )
        )
    }

    private fun OwnerDomainModel.toUiModel(): OwnerUiModel {
        return OwnerUiModel(imageUrl = imageUrl, name = name)
    }
}