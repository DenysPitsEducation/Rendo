package com.rendo.feature.rents.ui.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.HighlightOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.rendo.core.theme.LocalThemeIsDark
import com.rendo.core.utils.formatPrice
import com.rendo.feature.rents.domain.model.DropdownAction
import com.rendo.feature.rents.domain.model.RentDomainModel
import com.rendo.feature.rents.ui.model.DropdownItemUiModel
import com.rendo.feature.rents.ui.model.RentUiModel
import com.rendo.feature.rents.ui.model.RentsUiModel
import com.rendo.feature.rents.ui.model.StatusUiModel

class RentsUiMapper {
    @Composable
    fun mapToUiModel(model: List<RentDomainModel>): RentsUiModel = model.run {
        val (rentIns, rentOuts) = model.partition { it.type == RentDomainModel.Type.RENT_IN }
        return RentsUiModel(
            rentIns = rentIns.map { it.toUiModel() },
            rentOuts = rentOuts.map { it.toUiModel() }
        )
    }

    @Composable
    private fun RentDomainModel.toUiModel(): RentUiModel {
        val isDark by LocalThemeIsDark.current
        val orangeColor = if (isDark) Color(color = 0xFFECA537) else Color(color = 0xFFCD5915)
        val redColor = if (isDark) Color(color = 0xFFFF6444) else Color(color = 0xFFDE0404)
        val greenColor = if (isDark) Color(color = 0xFF54B44B) else Color(color = 0xFF188711)
        return RentUiModel(
            id = id,
            name = name,
            imageUrl = imageUrl,
            status = when (status) {
                RentDomainModel.Status.WAITING_FOR_ACCEPTANCE -> StatusUiModel("Waiting for acceptance", orangeColor)
                RentDomainModel.Status.ACCEPTED -> StatusUiModel("Accepted", greenColor)
                RentDomainModel.Status.REJECTED -> StatusUiModel("Rejected", redColor)
                RentDomainModel.Status.CANCELLED -> StatusUiModel("Cancelled", redColor)
            },
            dates = dates,
            price = price.formatPrice(currency),
            showAcceptanceButtons = status == RentDomainModel.Status.WAITING_FOR_ACCEPTANCE && type == RentDomainModel.Type.RENT_OUT,
            dropdownItems = listOfNotNull(
                DropdownItemUiModel(
                    action = DropdownAction.CANCEL_RENT,
                    icon = Icons.Outlined.HighlightOff,
                    text = "Cancel the rent"
                ).takeIf { type == RentDomainModel.Type.RENT_IN && status == RentDomainModel.Status.WAITING_FOR_ACCEPTANCE || status == RentDomainModel.Status.ACCEPTED },
                DropdownItemUiModel(
                    action = DropdownAction.DELETE_RENT,
                    icon = Icons.Outlined.Delete,
                    text = "Delete the rent"
                ),
            )
        )
    }
}