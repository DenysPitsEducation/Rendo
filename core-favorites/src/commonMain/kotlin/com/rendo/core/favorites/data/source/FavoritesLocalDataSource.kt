package com.rendo.core.favorites.data.source

import com.rendo.core.product.ProductDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class FavoritesLocalDataSource {
    private val dataFlow = MutableStateFlow<List<ProductDomainModel>>(emptyList())

    fun getFlow(): Flow<List<ProductDomainModel>> = dataFlow

    fun get() = dataFlow.value

    fun set(products: List<ProductDomainModel>) {
        dataFlow.value = products
    }
}