package com.rendo.feature.product.details.domain.mvi

import com.rendo.feature.product.details.domain.model.DateRangeDomainModel
import com.rendo.feature.product.details.domain.model.PhoneFieldDomainModel
import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel

internal sealed class ProductDetailsMessage {
    data class ProductUpdated(val product: ProductDetailsDomainModel) : ProductDetailsMessage()
    data class DateRangeChanged(val dateRange: DateRangeDomainModel) : ProductDetailsMessage()
    data class PhoneFieldUpdated(val phoneField: PhoneFieldDomainModel) : ProductDetailsMessage()
}
