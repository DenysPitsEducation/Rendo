package com.rendo.feature.advertisements.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rendo.feature.advertisements.domain.mvi.AdvertisementsIntent
import com.rendo.feature.advertisements.ui.OnUserInteraction
import com.rendo.feature.advertisements.ui.model.AdvertisementUiModel
import com.rendo.feature.advertisements.ui.model.AdvertisementsUiModel
import com.seiko.imageloader.ui.AutoSizeImage
import org.jetbrains.compose.resources.painterResource
import rendo.core.generated.resources.Res
import rendo.core.generated.resources.ill_no_photo

@Composable
internal fun AdvertisementsContentComposable(
    uiModel: AdvertisementsUiModel,
    onUserInteraction: OnUserInteraction,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier,
    ) {
        items(uiModel.advertisements) { advertisementUiModel ->
            AdvertisementComposable(advertisementUiModel, onUserInteraction)
        }
    }
}

@Composable
private fun AdvertisementComposable(
    uiModel: AdvertisementUiModel,
    onUserInteraction: OnUserInteraction,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .clickable { onUserInteraction(AdvertisementsIntent.AdvertisementClicked(uiModel.id)) }
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            AutoSizeImage(
                url = uiModel.imageUrl.orEmpty(),
                placeholderPainter = { painterResource(Res.drawable.ill_no_photo) },
                errorPainter = { painterResource(Res.drawable.ill_no_photo) },
                contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(8.dp)).size(88.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = uiModel.name, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = uiModel.price, style = MaterialTheme.typography.titleMedium)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = { onUserInteraction(AdvertisementsIntent.DeleteButtonClicked(uiModel.id)) },
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            colors = ButtonDefaults.outlinedButtonColors(),
        ) {
            Text("Delete the advertisement")
        }
    }
}
