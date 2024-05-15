package com.rendo.feature.product.details.domain.mvi

import com.rendo.feature.product.details.domain.model.DateRangeDomainModel
import com.rendo.feature.product.details.domain.model.PhoneFieldDomainModel
import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel

internal data class ProductDetailsState(
    val product: ProductDetailsDomainModel?,
    val phoneField: PhoneFieldDomainModel,
    val dateRangeDialogState: DateRangeDomainModel,
)
