package com.rendo.feature.product.details.domain.mvi

import com.arkivanov.mvikotlin.core.store.Store

internal typealias ProductDetailsStore = Store<ProductDetailsIntent, ProductDetailsState, ProductDetailsLabel>
