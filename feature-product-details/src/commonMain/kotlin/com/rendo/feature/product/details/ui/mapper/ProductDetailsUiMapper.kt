package com.rendo.feature.product.details.ui.mapper

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.runtime.Composable
import com.raedghazal.kotlinx_datetime_ext.LocalDateTimeFormatter
import com.raedghazal.kotlinx_datetime_ext.Locale
import com.raedghazal.kotlinx_datetime_ext.atStartOfDay
import com.raedghazal.kotlinx_datetime_ext.now
import com.rendo.core.utils.formatPrice
import com.rendo.core.utils.fromEpochMilliseconds
import com.rendo.core.utils.iterator
import com.rendo.feature.product.details.domain.model.OwnerDomainModel
import com.rendo.feature.product.details.domain.mvi.ProductDetailsState
import com.rendo.feature.product.details.ui.model.DatePickerUiModel
import com.rendo.feature.product.details.ui.model.OwnerUiModel
import com.rendo.feature.product.details.ui.model.ProductDetailsUiModel
import com.rendo.feature.product.details.ui.model.TextFieldUiModel
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import org.jetbrains.compose.resources.stringResource
import rendo.feature_product_details.generated.resources.Res
import rendo.feature_product_details.generated.resources.rent_period_unit


internal class ProductDetailsUiMapper {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun mapToUiModel(state: ProductDetailsState): ProductDetailsUiModel? = state.product?.run {
        val formatter = LocalDateTimeFormatter.ofPattern("dd MMMM", Locale.default())
        return ProductDetailsUiModel(
            id = id,
            name = name,
            description = description,
            imageUrls = imageUrls,
            price = "${price.formatPrice()}$currency / ${stringResource(Res.string.rent_period_unit)}",
            isInFavorites = isInFavorites,
            phoneField = TextFieldUiModel(state.phoneField.text, state.phoneField.errorText),
            pickupDate = formatter.format(pickupDate),
            returnDate = formatter.format(returnDate),
            totalPrice = "${totalPrice.formatPrice()}$currency",
            ownerUiModel = owner.toUiModel(),
            datePickerUiModel = DatePickerUiModel(
                initialPickupDateMillis = pickupDate.atStartOfDay().toInstant(TimeZone.UTC).toEpochMilliseconds(),
                initialReturnDateMillis = returnDate.atStartOfDay().toInstant(TimeZone.UTC).toEpochMilliseconds(),
                selectableDates = object : SelectableDates {
                    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                        val date: LocalDate = LocalDate.fromEpochMilliseconds(utcTimeMillis)
                        val nowDate = LocalDate.now(TimeZone.UTC)
                        return date >= nowDate && date !in prohibitedDates
                    }
                },
                isButtonEnabled = isDateRangeValid(
                    pickupDate = state.dateRangeDialogState.pickupDate,
                    returnDate = state.dateRangeDialogState.returnDate,
                    prohibitedDates = prohibitedDates,
                ),
            )
        )
    }

    private fun OwnerDomainModel.toUiModel(): OwnerUiModel {
        return OwnerUiModel(imageUrl = imageUrl, name = name)
    }

    private fun isDateRangeValid(pickupDate: LocalDate?, returnDate: LocalDate?, prohibitedDates: List<LocalDate>): Boolean {
        if (pickupDate == null || returnDate == null) return false

        for (date in pickupDate..returnDate) {
            if (date in prohibitedDates) return false
        }

        return true
    }
}