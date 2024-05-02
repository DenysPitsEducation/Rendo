package com.rendo.core.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.seiko.imageloader.ui.AutoSizeImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductComposable(
    model: ProductUiModel,
    onProductClick: () -> Unit,
    onFavoriteButtonClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.clip(RoundedCornerShape(8.dp)).background(MaterialTheme.colorScheme.background).clickable {
        onProductClick()
    }) {
        Column {
            AutoSizeImage(
                url = model.imageUrl.orEmpty(),
                contentDescription = null,
                modifier = Modifier.aspectRatio(1f),
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = model.name,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = model.price,
                maxLines = 1,
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
            IconButton(
                onClick = { onFavoriteButtonClick() },
                modifier = Modifier.align(Alignment.TopEnd).padding(4.dp).size(40.dp).background(Color.White, CircleShape)
            ) {
                Icon(
                    painter = rememberVectorPainter(if (model.isInFavorites) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder),
                    null,
                )
            }
        }
    }
}
