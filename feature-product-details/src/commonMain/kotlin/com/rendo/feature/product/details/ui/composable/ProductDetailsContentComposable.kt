package com.rendo.feature.product.details.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.rendo.core.phone.PhoneNumberVisualTransformation
import com.rendo.feature.product.details.domain.mvi.ProductDetailsIntent
import com.rendo.feature.product.details.ui.model.ProductDetailsUiModel
import com.seiko.imageloader.ui.AutoSizeImage
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import rendo.core.generated.resources.ill_no_photo
import rendo.feature_product_details.generated.resources.Res
import rendo.feature_product_details.generated.resources.change_dates_button
import rendo.feature_product_details.generated.resources.final_price
import rendo.feature_product_details.generated.resources.owner_button
import rendo.feature_product_details.generated.resources.phone_number_label
import rendo.feature_product_details.generated.resources.pickup_date
import rendo.feature_product_details.generated.resources.product_description
import rendo.feature_product_details.generated.resources.rent_button
import rendo.feature_product_details.generated.resources.return_date
import rendo.core.generated.resources.Res as CoreRes

@Composable
internal fun ProductDetailsContentComposable(
    model: ProductDetailsUiModel,
    onUserInteraction: OnUserInteraction,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        HeaderBlock(model = model)
        Spacer(modifier = Modifier.height(8.dp))
        RentBlock(model = model, onUserInteraction = onUserInteraction)
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.padding(horizontal = 8.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(text = stringResource(Res.string.product_description), style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = model.description)
        }
        Spacer(modifier = Modifier.height(8.dp))
        OwnerInfoBlock(model = model, onUserInteraction = onUserInteraction)
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun HeaderBlock(model: ProductDetailsUiModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.background,
                RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
            )
    ) {
        if (model.imageUrls.isNotEmpty()) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(items = model.imageUrls) {
                    AutoSizeImage(
                        url = it,
                        contentDescription = null,
                        contentScale = ContentScale.FillHeight,
                        modifier = Modifier
                            .clip(RoundedCornerShape(16.dp))
                            .height(250.dp),
                    )
                }
            }
        } else {
            Image(
                painter = painterResource(CoreRes.drawable.ill_no_photo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .height(250.dp)
                    .aspectRatio(1f),
            )
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
}

@Composable
private fun RentBlock(model: ProductDetailsUiModel, onUserInteraction: OnUserInteraction) {
    val phoneHintStyle = MaterialTheme.typography.bodyLarge
        .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
    val phoneTextStyle = MaterialTheme.typography.bodyLarge
        .copy(color = MaterialTheme.colorScheme.onSurface)
    Column(
        modifier = Modifier.padding(horizontal = 8.dp)
            .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row {
            Text(text = stringResource(Res.string.pickup_date))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = model.pickupDate)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text(text = stringResource(Res.string.return_date))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = model.returnDate)
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row {
            Text(text = stringResource(Res.string.final_price))
            Spacer(modifier = Modifier.weight(1f))
            Text(text = model.totalPrice)
        }
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = model.phoneField.text,
            onValueChange = {
                onUserInteraction(ProductDetailsIntent.PhoneTextFieldChanged(it))
            },
            label = { Text(stringResource(Res.string.phone_number_label)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PhoneNumberVisualTransformation(
                hintFontStyle = phoneHintStyle,
                textFontStyle = phoneTextStyle,
            ),
            isError = model.phoneField.errorText != null,
            supportingText = model.phoneField.errorText?.let { { Text(stringResource(it)) } },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onUserInteraction(ProductDetailsIntent.RentButtonClicked) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(Res.string.rent_button))
        }
        Button(
            onClick = { onUserInteraction(ProductDetailsIntent.ChangeDatesButtonClicked) },
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            colors = ButtonDefaults.outlinedButtonColors(),
        ) {
            Text(text = stringResource(Res.string.change_dates_button))
        }
    }
}

@Composable
private fun OwnerInfoBlock(model: ProductDetailsUiModel, onUserInteraction: OnUserInteraction) {
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
            onClick = { onUserInteraction(ProductDetailsIntent.CallOwnerButtonClicked) },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(text = stringResource(Res.string.owner_button))
        }
    }
}