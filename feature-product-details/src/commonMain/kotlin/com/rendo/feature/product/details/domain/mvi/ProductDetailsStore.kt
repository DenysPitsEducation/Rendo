package com.rendo.feature.product.details.domain.mvi

import com.arkivanov.mvikotlin.core.store.Store

typealias ProductDetailsStore = Store<ProductDetailsIntent, ProductDetailsState, ProductDetailsLabel>
