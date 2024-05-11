package com.rendo.core.favorites.data.source

import com.rendo.core.product.ProductDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

internal class FavoritesLocalDataSource {
    private val dataFlow = MutableStateFlow(listOf(
        ProductDomainModel(
            id = "2",
            name = "DreamGoggles VR",
            imageUrl = "https://picsum.photos/200?random=2",
            price = 149.99,
            currency = "₴",
            isInFavorites = true
        ),
        ProductDomainModel(
            id = "3",
            name = "EcoBot 5000",
            imageUrl = "https://picsum.photos/200?random=3",
            price = 79.99,
            currency = "₴",
            isInFavorites = true
        ),
    ))

    fun getFlow(): Flow<List<ProductDomainModel>> = dataFlow

    fun get() = dataFlow.value

    fun set(products: List<ProductDomainModel>) {
        dataFlow.value = products
    }
}