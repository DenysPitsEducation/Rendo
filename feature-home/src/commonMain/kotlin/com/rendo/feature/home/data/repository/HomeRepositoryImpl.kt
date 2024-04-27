package com.rendo.feature.home.data.repository

import com.rendo.feature.home.domain.model.ProductDomainModel
import com.rendo.feature.home.domain.repository.HomeRepository

class HomeRepositoryImpl : HomeRepository {
    override fun getProducts(): List<ProductDomainModel> {
        return listOf(
            ProductDomainModel(
                id = 1,
                name = "HyperDrive 3000",
                imageUrl = null,
                price = 199.99,
                currency = "USD",
                isInFavorites = false
            ),
            ProductDomainModel(
                id = 2,
                name = "DreamGoggles VR",
                imageUrl = null,
                price = 149.99,
                currency = "USD",
                isInFavorites = true
            ),
            ProductDomainModel(
                id = 3,
                name = "EcoBot 5000",
                imageUrl = null,
                price = 79.99,
                currency = "USD",
                isInFavorites = false
            ),
            ProductDomainModel(
                id = 4,
                name = "InfiniteBattery Power Bank",
                imageUrl = null,
                price = 49.99,
                currency = "USD",
                isInFavorites = true
            ),
            ProductDomainModel(
                id = 5,
                name = "IntelliToaster",
                imageUrl = null,
                price = 29.99,
                currency = "USD",
                isInFavorites = false
            ),
            ProductDomainModel(
                id = 6,
                name = "NanoBot Cleaning Drones",
                imageUrl = null,
                price = 299.99,
                currency = "USD",
                isInFavorites = true
            ),
            ProductDomainModel(
                id = 7,
                name = "QuantumWave Wireless Charger",
                imageUrl = null,
                price = 89.99,
                currency = "USD",
                isInFavorites = false
            ),
            ProductDomainModel(
                id = 8,
                name = "MindSync Brainwave Headset",
                imageUrl = null,
                price = 199.99,
                currency = "USD",
                isInFavorites = true
            ),
            ProductDomainModel(
                id = 9,
                name = "EcoFuel Air Purifier",
                imageUrl = null,
                price = 149.99,
                currency = "USD",
                isInFavorites = false
            ),
            ProductDomainModel(
                id = 10,
                name = "GalacticGlow LED Plant Light",
                imageUrl = null,
                price = 39.99,
                currency = "USD",
                isInFavorites = true
            )
        )
    }
}