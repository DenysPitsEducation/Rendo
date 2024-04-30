package com.rendo.feature.product.details.data.repository

import com.rendo.feature.product.details.domain.model.OwnerDomainModel
import com.rendo.feature.product.details.domain.model.ProductDetailsDomainModel
import com.rendo.feature.product.details.domain.repository.ProductDetailsRepository
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month

class ProductDetailsRepositoryImpl : ProductDetailsRepository {
    override fun getProductDetails(id: Long): ProductDetailsDomainModel {
        return ProductDetailsDomainModel(
            id = 1,
            name = "HyperDrive 3000",
            description = "It is the description of the HyperDrive 3000 product. It is awesome",
            imageUrls = listOf(
                "https://picsum.photos/600?random=1",
                "https://picsum.photos/600?random=2",
                "https://picsum.photos/600?random=3"
            ),
            price = 199.99,
            currency = "$",
            isInFavorites = true,
            owner = OwnerDomainModel(
                id = 1,
                name = "George",
                phone = "+380951234567",
                imageUrl = "https://picsum.photos/100?random=4",
            ),
            pickupDate = LocalDate(2024, Month.MAY, 9),
            returnDate = LocalDate(2024, Month.MAY, 11),
            totalPrice = 199.99 * 3,
        )
    }
}