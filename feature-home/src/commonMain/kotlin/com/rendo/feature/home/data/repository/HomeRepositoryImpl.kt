package com.rendo.feature.home.data.repository

import com.rendo.core.product.ProductDomainModel
import com.rendo.feature.home.domain.repository.HomeRepository

class HomeRepositoryImpl : HomeRepository {
    override fun getProducts(): List<ProductDomainModel> {
        return listOf(
            ProductDomainModel(
                id = 1,
                name = "HyperDrive 3000",
                imageUrl = "https://picsum.photos/200?random=1",
                price = 199.99,
                currency = "$",
                isInFavorites = false
            ),
            ProductDomainModel(
                id = 2,
                name = "DreamGoggles VR",
                imageUrl = "https://picsum.photos/200?random=2",
                price = 149.99,
                currency = "$",
                isInFavorites = true
            ),
            ProductDomainModel(
                id = 3,
                name = "EcoBot 5000",
                imageUrl = "https://picsum.photos/200?random=3",
                price = 79.99,
                currency = "$",
                isInFavorites = false
            ),
            ProductDomainModel(
                id = 4,
                name = "InfiniteBattery Power Bank",
                imageUrl = "https://picsum.photos/200?random=4",
                price = 49.99,
                currency = "$",
                isInFavorites = true
            ),
            ProductDomainModel(
                id = 5,
                name = "IntelliToaster",
                imageUrl = "https://picsum.photos/200?random=5",
                price = 29.99,
                currency = "$",
                isInFavorites = false
            ),
            ProductDomainModel(
                id = 6,
                name = "NanoBot Cleaning Drones",
                imageUrl = "https://picsum.photos/200?random=6",
                price = 299.99,
                currency = "$",
                isInFavorites = true
            ),
            ProductDomainModel(
                id = 7,
                name = "QuantumWave Wireless Charger",
                imageUrl = "https://picsum.photos/200?random=7",
                price = 89.99,
                currency = "$",
                isInFavorites = false
            ),
            ProductDomainModel(
                id = 8,
                name = "MindSync Brainwave Headset",
                imageUrl = "https://picsum.photos/200?random=8",
                price = 199.99,
                currency = "$",
                isInFavorites = true
            ),
            ProductDomainModel(
                id = 9,
                name = "EcoFuel Air Purifier",
                imageUrl = "https://picsum.photos/200?random=9",
                price = 149.99,
                currency = "$",
                isInFavorites = false
            ),
            ProductDomainModel(
                id = 10,
                name = "GalacticGlow LED Plant Light",
                imageUrl = "https://picsum.photos/200?random=10",
                price = 39.99,
                currency = "$",
                isInFavorites = true
            )
        )
    }
}