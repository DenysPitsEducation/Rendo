package com.rendo.feature.home.ui.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rendo.feature.home.ui.model.ProductUiModel

@Composable
fun createProductUiModelMock(id: Long): ProductUiModel {
    return ProductUiModel(
        id = id,
        name = "Product name",
        imageUrl = "https://picsum.photos/200",
        price = "5 $",
        isInFavorites = false
    )
}

@Composable
@Preview
private fun ProductComposablePreview() {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        ProductComposable(
            model = createProductUiModelMock(1),
            modifier = Modifier.weight(1f),
        )
        Spacer(modifier = Modifier.width(8.dp))
        ProductComposable(
            model = createProductUiModelMock(2),
            modifier = Modifier.weight(1f),
        )
    }
}