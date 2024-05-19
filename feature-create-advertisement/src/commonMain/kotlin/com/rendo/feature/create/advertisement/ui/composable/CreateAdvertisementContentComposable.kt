package com.rendo.feature.create.advertisement.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.darkrockstudios.libraries.mpfilepicker.MultipleFilePicker
import com.rendo.core.phone.PhoneNumberVisualTransformation
import com.rendo.feature.create.advertisement.domain.model.ImageDomainModel
import com.rendo.feature.create.advertisement.domain.model.InputType
import com.rendo.feature.create.advertisement.domain.mvi.CreateAdvertisementIntent
import com.rendo.feature.create.advertisement.ui.OnUserInteraction
import com.rendo.feature.create.advertisement.ui.image.ImageHelper
import com.rendo.feature.create.advertisement.ui.model.CreateAdvertisementUiModel
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.ui.AutoSizeImage
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import rendo.feature_create_advertisement.generated.resources.Res
import rendo.feature_create_advertisement.generated.resources.create_advertisement
import rendo.feature_create_advertisement.generated.resources.person_name
import rendo.feature_create_advertisement.generated.resources.personal_info
import rendo.feature_create_advertisement.generated.resources.phone_number
import rendo.feature_create_advertisement.generated.resources.placeholder_product_description
import rendo.feature_create_advertisement.generated.resources.placeholder_product_name
import rendo.feature_create_advertisement.generated.resources.placeholder_product_price
import rendo.feature_create_advertisement.generated.resources.product_pictures
import rendo.feature_create_advertisement.generated.resources.rental_product_info

@Composable
internal fun CreateAdvertisementContentComposable(
    uiModel: CreateAdvertisementUiModel.Content,
    onUserInteraction: OnUserInteraction
) {
    val imageHelper: ImageHelper = koinInject()
    var showImagePicker by remember { mutableStateOf(false) }
    val fileType = listOf("jpg", "png", "webp")
    MultipleFilePicker(show = showImagePicker, fileExtensions = fileType) { files ->
        val imageFiles = files?.map {
            ImageDomainModel(
                image = it.platformFile,
                file = imageHelper.getFileFromPath(it.platformFile)
            )
        }.orEmpty()
        onUserInteraction(CreateAdvertisementIntent.ImagesSelected(imageFiles))
        showImagePicker = false
    }
    val imageModifier = Modifier.size(120.dp)
        .clip(RoundedCornerShape(8.dp))
        .border(
            1.dp,
            MaterialTheme.colorScheme.onBackground,
            RoundedCornerShape(8.dp)
        )
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(Res.string.product_pictures),
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(uiModel.images) { image ->
                    AutoSizeImage(
                        ImageRequest(image),
                        contentDescription = null,
                        modifier = imageModifier,
                    )
                }
                item {
                    Box(modifier = imageModifier.clickable { showImagePicker = true }
                    ) {
                        Icon(
                            Icons.Filled.AddCircleOutline,
                            null,
                            modifier = Modifier.size(32.dp).align(Alignment.Center)
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = stringResource(Res.string.rental_product_info),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                uiModel.productName.text,
                onValueChange = {
                    onUserInteraction(
                        CreateAdvertisementIntent.InputChanged(
                            it,
                            InputType.PRODUCT_NAME
                        )
                    )
                },
                placeholder = { Text(stringResource(Res.string.placeholder_product_name)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                isError = uiModel.productName.errorText != null,
                supportingText = uiModel.productName.errorText?.let { { Text(stringResource(it)) } },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                uiModel.productDescription.text,
                onValueChange = {
                    onUserInteraction(
                        CreateAdvertisementIntent.InputChanged(
                            it,
                            InputType.PRODUCT_DESCRIPTION
                        )
                    )
                },
                placeholder = { Text(stringResource(Res.string.placeholder_product_description)) },
                minLines = 4,
                maxLines = 4,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                isError = uiModel.productDescription.errorText != null,
                supportingText = uiModel.productDescription.errorText?.let { { Text(stringResource(it)) } },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                uiModel.productPrice.text,
                onValueChange = {
                    onUserInteraction(
                        CreateAdvertisementIntent.InputChanged(
                            it,
                            InputType.PRODUCT_PRICE
                        )
                    )
                },
                placeholder = { Text(stringResource(Res.string.placeholder_product_price)) },
                suffix = { Text("₴") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next,
                ),
                isError = uiModel.productPrice.errorText != null,
                supportingText = uiModel.productPrice.errorText?.let { { Text(stringResource(it)) } },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier.padding(8.dp)
                .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
                .padding(16.dp)
        ) {
            Text(stringResource(Res.string.personal_info), style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                uiModel.ownerName.text,
                onValueChange = {
                    onUserInteraction(
                        CreateAdvertisementIntent.InputChanged(
                            it,
                            InputType.OWNER_NAME
                        )
                    )
                },
                placeholder = { Text(stringResource(Res.string.person_name)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                isError = uiModel.ownerName.errorText != null,
                supportingText = uiModel.ownerName.errorText?.let { { Text(stringResource(it)) } },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            val hintFontStyle = MaterialTheme.typography.bodyLarge
                .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
            val textFontStyle = MaterialTheme.typography.bodyLarge
                .copy(color = MaterialTheme.colorScheme.onSurface)
            OutlinedTextField(
                uiModel.ownerPhoneNumber.text,
                onValueChange = {
                    onUserInteraction(
                        CreateAdvertisementIntent.InputChanged(it, InputType.OWNER_PHONE_NUMBER)
                    )
                },
                placeholder = { Text(stringResource(Res.string.phone_number)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PhoneNumberVisualTransformation(
                    hintFontStyle = hintFontStyle,
                    textFontStyle = textFontStyle,
                ),
                isError = uiModel.ownerPhoneNumber.errorText != null,
                supportingText = uiModel.ownerPhoneNumber.errorText?.let { { Text(stringResource(it)) } },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onUserInteraction.invoke(CreateAdvertisementIntent.CreateAdvertisementButtonClicked) },
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
        ) {
            Text(stringResource(Res.string.create_advertisement))
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}
