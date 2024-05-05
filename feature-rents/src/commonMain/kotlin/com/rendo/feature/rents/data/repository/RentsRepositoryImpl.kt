package com.rendo.feature.rents.data.repository

import com.rendo.feature.rents.domain.model.RentDomainModel
import com.rendo.feature.rents.domain.repository.RentsRepository

class RentsRepositoryImpl : RentsRepository {
    override fun getRents(): List<RentDomainModel> {
        return listOf(
            RentDomainModel(
                id = 1,
                productId = 1,
                name = "HyperDrive 3000",
                imageUrl = "https://picsum.photos/200?random=1",
                status = RentDomainModel.Status.WAITING_FOR_ACCEPTANCE,
                dates = "27 May - 03 June",
                price = 300.0,
                currency = "₴",
                type = RentDomainModel.Type.RENT_IN,
                phoneNumber = "+380951234567"
            ),
            RentDomainModel(
                id = 2,
                productId = 2,
                name = "DreamGoggles VR",
                imageUrl = "https://picsum.photos/200?random=2",
                status = RentDomainModel.Status.ACCEPTED,
                dates = "4 May - 18 May",
                price = 1000.0,
                currency = "₴",
                type = RentDomainModel.Type.RENT_IN,
                phoneNumber = "+380951234567"
            ),
            RentDomainModel(
                id = 3,
                productId = 3,
                name = "EcoBot 5000",
                imageUrl = "https://picsum.photos/200?random=3",
                status = RentDomainModel.Status.WAITING_FOR_ACCEPTANCE,
                dates = "4 May - 18 May",
                price = 1000.0,
                currency = "₴",
                type = RentDomainModel.Type.RENT_OUT,
                phoneNumber = "+380951234567"
            ),
            RentDomainModel(
                id = 4,
                productId = 4,
                name = "InfiniteBattery Power Bank",
                imageUrl = "https://picsum.photos/200?random=4",
                status = RentDomainModel.Status.CANCELLED,
                dates = "4 May - 18 May",
                price = 1000.0,
                currency = "₴",
                type = RentDomainModel.Type.RENT_OUT,
                phoneNumber = "+380951234567"
            )
        )
    }
}