package com.rendo.feature.product.details.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.rendo.feature.product.details.ui.model.ProductDetailsUiModel
import com.seiko.imageloader.ui.AutoSizeImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsContentComposable(
    model: ProductDetailsUiModel,
    modifier: Modifier = Modifier,
    openBottomSheet: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier.background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
        ) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(items = model.imageUrls) {
                    AutoSizeImage(
                        url = it,
                        contentDescription = null,
                        modifier = Modifier.clip(RoundedCornerShape(16.dp))
                            .fillParentMaxWidth(0.8f)
                            .aspectRatio(1f),
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = model.name,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = model.price,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Row {
                Text(text = "Pickup date:")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = model.pickupDate)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(text = "Return date:")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = model.returnDate)
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row {
                Text(text = "Final price:")
                Spacer(modifier = Modifier.weight(1f))
                Text(text = model.totalPrice)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /*todo*/ },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Rent")
            }
            Button(
                onClick = { openBottomSheet() },
                modifier = Modifier.fillMaxWidth(),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                colors = ButtonDefaults.outlinedButtonColors(),
            ) {
                Text(text = "Change dates")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(text = "Description", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = model.description)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AutoSizeImage(
                    url = model.ownerUiModel.imageUrl.orEmpty(),
                    contentDescription = null,
                    modifier = Modifier.size(48.dp).clip(CircleShape),
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(text = model.ownerUiModel.name)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /*todo*/ },
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(text = "Call the owner")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}