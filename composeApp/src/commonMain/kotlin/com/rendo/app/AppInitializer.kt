package com.rendo.app

import com.rendo.core.favorites.domain.repository.FavoritesRepository
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class AppInitializer(
    private val favoritesRepository: FavoritesRepository,
) {
    fun initialize() {
        CoroutineScope(Dispatchers.IO).launch {
            //initProductDetails()
        }
    }

    private suspend fun initProductDetails() {
        val list = listOf(
            ProductDetailsTest(
                name = "HyperDrive 3000",
                description = "It is the description of the product. It is awesome",
                imageUrls = listOf(
                    "https://picsum.photos/600?random=1",
                    "https://picsum.photos/600?random=2",
                    "https://picsum.photos/600?random=3"
                ),
                price = 199.99,
                currency = "₴",
                owner = OwnerTest(
                    id = "1",
                    name = "George",
                    phone = "+380951234567",
                    imageUrl = "https://picsum.photos/100?random=4",
                ),
                prohibitedDates = listOf(
                    "2024-06-01",
                    "2024-06-04",
                ),
            ),
            ProductDetailsTest(
                name = "DreamGoggles VR",
                description = "It is the description of the product. It is awesome",
                imageUrls = listOf(
                    "https://picsum.photos/600?random=1",
                    "https://picsum.photos/600?random=2",
                    "https://picsum.photos/600?random=3"
                ),
                price = 149.99,
                currency = "₴",
                owner = OwnerTest(
                    id = "1",
                    name = "George",
                    phone = "+380951234567",
                    imageUrl = "https://picsum.photos/100?random=4",
                ),
                prohibitedDates = listOf(
                    "2024-06-01",
                    "2024-06-04",
                ),
            ),
            ProductDetailsTest(
                name = "EcoBot 5000",
                description = "It is the description of the product. It is awesome",
                imageUrls = listOf(
                    "https://picsum.photos/600?random=1",
                    "https://picsum.photos/600?random=2",
                    "https://picsum.photos/600?random=3"
                ),
                price = 79.99,
                currency = "₴",
                owner = OwnerTest(
                    id = "1",
                    name = "George",
                    phone = "+380951234567",
                    imageUrl = "https://picsum.photos/100?random=4",
                ),
                prohibitedDates = listOf(
                    "2024-06-01",
                    "2024-06-04",
                ),
            ),
            ProductDetailsTest(
                name = "InfiniteBattery Power Bank",
                description = "It is the description of the product. It is awesome",
                imageUrls = listOf(
                    "https://picsum.photos/600?random=1",
                    "https://picsum.photos/600?random=2",
                    "https://picsum.photos/600?random=3"
                ),
                price = 49.99,
                currency = "₴",
                owner = OwnerTest(
                    id = "1",
                    name = "George",
                    phone = "+380951234567",
                    imageUrl = "https://picsum.photos/100?random=4",
                ),
                prohibitedDates = listOf(
                    "2024-06-01",
                    "2024-06-04",
                ),
            ),
            ProductDetailsTest(
                name = "IntelliToaster",
                description = "It is the description of the product. It is awesome",
                imageUrls = listOf(
                    "https://picsum.photos/600?random=1",
                    "https://picsum.photos/600?random=2",
                    "https://picsum.photos/600?random=3"
                ),
                price = 29.99,
                currency = "₴",
                owner = OwnerTest(
                    id = "1",
                    name = "George",
                    phone = "+380951234567",
                    imageUrl = "https://picsum.photos/100?random=4",
                ),
                prohibitedDates = listOf(
                    "2024-06-01",
                    "2024-06-04",
                ),
            ),
            ProductDetailsTest(
                name = "NanoBot Cleaning Drones",
                description = "It is the description of the product. It is awesome",
                imageUrls = listOf(
                    "https://picsum.photos/600?random=1",
                    "https://picsum.photos/600?random=2",
                    "https://picsum.photos/600?random=3"
                ),
                price = 299.99,
                currency = "₴",
                owner = OwnerTest(
                    id = "1",
                    name = "George",
                    phone = "+380951234567",
                    imageUrl = "https://picsum.photos/100?random=4",
                ),
                prohibitedDates = listOf(
                    "2024-06-01",
                    "2024-06-04",
                ),
            ),
            ProductDetailsTest(
                name = "QuantumWave Wireless Charger",
                description = "It is the description of the product. It is awesome",
                imageUrls = listOf(
                    "https://picsum.photos/600?random=1",
                    "https://picsum.photos/600?random=2",
                    "https://picsum.photos/600?random=3"
                ),
                price = 89.99,
                currency = "₴",
                owner = OwnerTest(
                    id = "1",
                    name = "George",
                    phone = "+380951234567",
                    imageUrl = "https://picsum.photos/100?random=4",
                ),
                prohibitedDates = listOf(
                    "2024-06-01",
                    "2024-06-04",
                ),
            ),
            ProductDetailsTest(
                name = "MindSync Brainwave Headset",
                description = "It is the description of the product. It is awesome",
                imageUrls = listOf(
                    "https://picsum.photos/600?random=1",
                    "https://picsum.photos/600?random=2",
                    "https://picsum.photos/600?random=3"
                ),
                price = 199.99,
                currency = "₴",
                owner = OwnerTest(
                    id = "1",
                    name = "George",
                    phone = "+380951234567",
                    imageUrl = "https://picsum.photos/100?random=4",
                ),
                prohibitedDates = listOf(
                    "2024-06-01",
                    "2024-06-04",
                ),
            ),
            ProductDetailsTest(
                name = "EcoFuel Air Purifier",
                description = "It is the description of the product. It is awesome",
                imageUrls = listOf(
                    "https://picsum.photos/600?random=1",
                    "https://picsum.photos/600?random=2",
                    "https://picsum.photos/600?random=3"
                ),
                price = 149.99,
                currency = "₴",
                owner = OwnerTest(
                    id = "1",
                    name = "George",
                    phone = "+380951234567",
                    imageUrl = "https://picsum.photos/100?random=4",
                ),
                prohibitedDates = listOf(
                    "2024-06-01",
                    "2024-06-04",
                ),
            ),
            ProductDetailsTest(
                name = "GalacticGlow LED Plant Light",
                description = "It is the description of the product. It is awesome",
                imageUrls = listOf(
                    "https://picsum.photos/600?random=1",
                    "https://picsum.photos/600?random=2",
                    "https://picsum.photos/600?random=3"
                ),
                price = 39.99,
                currency = "₴",
                owner = OwnerTest(
                    id = "1",
                    name = "George",
                    phone = "+380951234567",
                    imageUrl = "https://picsum.photos/100?random=4",
                ),
                prohibitedDates = listOf(
                    "2024-06-01",
                    "2024-06-04",
                ),
            ),
        )

        val database = Firebase.firestore
        val collection = database.collection("product_details")

        Firebase.firestore.batch().apply {
            list.forEachIndexed { index, product ->
                val document = collection.document((index + 1).toString())
                set(document, product)
            }
        }.commit()
    }

    private suspend fun initProducts() {
        val list = listOf(
            ProductTest(
                name = "HyperDrive 3000",
                imageUrl = "https://picsum.photos/200?random=1",
                price = 199.99,
                currency = "₴",
            ),
            ProductTest(
                name = "DreamGoggles VR",
                imageUrl = "https://picsum.photos/200?random=2",
                price = 149.99,
                currency = "₴",
            ),
            ProductTest(
                name = "EcoBot 5000",
                imageUrl = "https://picsum.photos/200?random=3",
                price = 79.99,
                currency = "₴",
            ),
            ProductTest(
                name = "InfiniteBattery Power Bank",
                imageUrl = "https://picsum.photos/200?random=4",
                price = 49.99,
                currency = "₴",
            ),
            ProductTest(
                name = "IntelliToaster",
                imageUrl = "https://picsum.photos/200?random=5",
                price = 29.99,
                currency = "₴",
            ),
            ProductTest(
                name = "NanoBot Cleaning Drones",
                imageUrl = "https://picsum.photos/200?random=6",
                price = 299.99,
                currency = "₴",
            ),
            ProductTest(
                name = "QuantumWave Wireless Charger",
                imageUrl = "https://picsum.photos/200?random=7",
                price = 89.99,
                currency = "₴",
            ),
            ProductTest(
                name = "MindSync Brainwave Headset",
                imageUrl = "https://picsum.photos/200?random=8",
                price = 199.99,
                currency = "₴",
            ),
            ProductTest(
                name = "EcoFuel Air Purifier",
                imageUrl = "https://picsum.photos/200?random=9",
                price = 149.99,
                currency = "₴",
            ),
            ProductTest(
                name = "GalacticGlow LED Plant Light",
                imageUrl = "https://picsum.photos/200?random=10",
                price = 39.99,
                currency = "₴",
            )
        )

        val database = Firebase.firestore
        val collection = database.collection("products")

        Firebase.firestore.batch().apply {
            list.forEachIndexed { index, product ->
                val document = collection.document((index + 1).toString())
                set(document, product)
            }
        }.commit()
    }

    @Serializable
    data class ProductTest(
        val name: String,
        @SerialName("image_url")
        val imageUrl: String?,
        val price: Double,
        val currency: String,
    )

    @Serializable
    data class ProductDetailsTest(
        @SerialName("name")
        val name: String,
        @SerialName("description")
        val description: String,
        @SerialName("image_urls")
        val imageUrls: List<String>,
        @SerialName("price")
        val price: Double,
        @SerialName("currency")
        val currency: String,
        @SerialName("prohibited_dates")
        val prohibitedDates: List<String>,
        @SerialName("owner")
        val owner: OwnerTest,
    )

    @Serializable
    data class OwnerTest(
        @SerialName("id")
        val id: String,
        @SerialName("name")
        val name: String,
        @SerialName("phone")
        val phone: String,
        @SerialName("image_url")
        val imageUrl: String?,
    )
}