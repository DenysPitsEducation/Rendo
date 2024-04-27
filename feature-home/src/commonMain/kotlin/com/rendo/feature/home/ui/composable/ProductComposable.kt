package com.rendo.feature.home.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rendo.feature.home.ui.model.ProductUiModel
import com.seiko.imageloader.ui.AutoSizeImage

@Composable
fun ProductComposable(model: ProductUiModel, modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(MaterialTheme.colorScheme.surfaceContainer)) {
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
        IconButton(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.TopEnd)) {
            Icon(
                painter = rememberVectorPainter(if (model.isInFavorites) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder),
                null
            )
        }
    }
}
