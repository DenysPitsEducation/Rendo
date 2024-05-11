package com.rendo.feature.advertisements.ui.mapper

import androidx.compose.runtime.Composable
import com.rendo.core.utils.formatPrice
import com.rendo.feature.advertisements.domain.model.AdvertisementDomainModel
import com.rendo.feature.advertisements.ui.model.AdvertisementUiModel
import com.rendo.feature.advertisements.ui.model.AdvertisementsUiModel

internal class AdvertisementsUiMapper {
    @Composable
    fun mapToUiModel(model: List<AdvertisementDomainModel>): AdvertisementsUiModel = model.run {
        return AdvertisementsUiModel(
            advertisements = model.map { it.toUiModel() },
        )
    }

    @Composable
    private fun AdvertisementDomainModel.toUiModel(): AdvertisementUiModel {
        return AdvertisementUiModel(
            id = id,
            name = name,
            imageUrl = imageUrl,
            price = price.formatPrice(currency),
        )
    }
}