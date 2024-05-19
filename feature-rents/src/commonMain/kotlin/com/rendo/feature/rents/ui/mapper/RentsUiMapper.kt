package com.rendo.feature.rents.ui.mapper

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.HighlightOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import com.raedghazal.kotlinx_datetime_ext.LocalDateTimeFormatter
import com.raedghazal.kotlinx_datetime_ext.Locale
import com.rendo.core.theme.LocalThemeIsDark
import com.rendo.core.utils.formatPrice
import com.rendo.feature.rents.domain.model.DropdownAction
import com.rendo.feature.rents.domain.model.RentDomainModel
import com.rendo.feature.rents.ui.model.DropdownItemUiModel
import com.rendo.feature.rents.ui.model.RentUiModel
import com.rendo.feature.rents.ui.model.RentsUiModel
import com.rendo.feature.rents.ui.model.StatusUiModel
import org.jetbrains.compose.resources.stringResource
import rendo.feature_rents.generated.resources.Res
import rendo.feature_rents.generated.resources.action_cancel
import rendo.feature_rents.generated.resources.action_delete
import rendo.feature_rents.generated.resources.status_accepted
import rendo.feature_rents.generated.resources.status_cancelled
import rendo.feature_rents.generated.resources.status_rejected
import rendo.feature_rents.generated.resources.status_waiting_for_acceptance

internal class RentsUiMapper {
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
        val formatter = LocalDateTimeFormatter.ofPattern("dd MMMM", Locale.default())
        val pickupDateFormatted = formatter.format(pickupDate)
        val returnDateFormatted = formatter.format(returnDate)
        return RentUiModel(
            id = id,
            name = name,
            imageUrl = imageUrl,
            status = when (status) {
                RentDomainModel.Status.WAITING_FOR_ACCEPTANCE -> StatusUiModel(stringResource(Res.string.status_waiting_for_acceptance), orangeColor)
                RentDomainModel.Status.ACCEPTED -> StatusUiModel(stringResource(Res.string.status_accepted), greenColor)
                RentDomainModel.Status.REJECTED -> StatusUiModel(stringResource(Res.string.status_rejected), redColor)
                RentDomainModel.Status.CANCELLED -> StatusUiModel(stringResource(Res.string.status_cancelled), redColor)
            },
            dates = "$pickupDateFormatted - $returnDateFormatted",
            price = price.formatPrice(currency),
            showAcceptanceButtons = status == RentDomainModel.Status.WAITING_FOR_ACCEPTANCE && type == RentDomainModel.Type.RENT_OUT,
            dropdownItems = listOfNotNull(
                DropdownItemUiModel(
                    action = DropdownAction.CANCEL_RENT,
                    icon = Icons.Outlined.HighlightOff,
                    text = stringResource(Res.string.action_cancel)
                ).takeIf { type == RentDomainModel.Type.RENT_IN && status == RentDomainModel.Status.WAITING_FOR_ACCEPTANCE || status == RentDomainModel.Status.ACCEPTED },
                DropdownItemUiModel(
                    action = DropdownAction.DELETE_RENT,
                    icon = Icons.Outlined.Delete,
                    text = stringResource(Res.string.action_delete)
                ),
            )
        )
    }
}