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
import com.rendo.feature.create.advertisement.domain.model.InputType
import com.rendo.feature.create.advertisement.domain.mvi.CreateAdvertisementIntent
import com.rendo.feature.create.advertisement.ui.OnUserInteraction
import com.rendo.feature.create.advertisement.ui.model.CreateAdvertisementUiModel
import com.rendo.feature.create.advertisement.ui.phone.transformTextPhoneNumber
import com.seiko.imageloader.model.ImageRequest
import com.seiko.imageloader.ui.AutoSizeImage

@Composable
internal fun CreateAdvertisementContentComposable(
    uiModel: CreateAdvertisementUiModel,
    onUserInteraction: OnUserInteraction
) {
    var showImagePicker by remember { mutableStateOf(false) }
    var selectedImages by remember { mutableStateOf<List<Any>>(listOf()) }
    val fileType = listOf("jpg", "png", "webp")
    MultipleFilePicker(show = showImagePicker, fileExtensions = fileType) { files ->
        selectedImages = files?.map { it.platformFile }.orEmpty()
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
                text = "Product pictures",
                modifier = Modifier.padding(horizontal = 16.dp),
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                items(selectedImages) { image ->
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
                text = "Rental product info",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                uiModel.productName.text,
                onValueChange = {
                    onUserInteraction(CreateAdvertisementIntent.InputChanged(it, InputType.PRODUCT_NAME))
                },
                placeholder = { Text("Name of the product") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                isError = uiModel.productName.errorText != null,
                supportingText = uiModel.productName.errorText?.let { { Text(it) } },
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
                placeholder = { Text("Description of the product") },
                minLines = 4,
                maxLines = 4,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                isError = uiModel.productDescription.errorText != null,
                supportingText = uiModel.productDescription.errorText?.let { { Text(it) } },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                uiModel.productPrice.text,
                onValueChange = {
                    onUserInteraction(CreateAdvertisementIntent.InputChanged(it, InputType.PRODUCT_PRICE))
                },
                placeholder = { Text("Price per one day") },
                suffix = { Text("₴") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next,
                ),
                isError = uiModel.productPrice.errorText != null,
                supportingText = uiModel.productPrice.errorText?.let { { Text(it) } },
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
            Text("Personal info", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                uiModel.ownerName.text,
                onValueChange = {
                    onUserInteraction(CreateAdvertisementIntent.InputChanged(it, InputType.OWNER_NAME))
                },
                placeholder = { Text("Name of the contact person") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                isError = uiModel.ownerName.errorText != null,
                supportingText = uiModel.ownerName.errorText?.let { { Text(it) } },
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
                placeholder = { Text("Phone number") },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                visualTransformation = {
                    transformTextPhoneNumber(
                        text = it,
                        hintFontStyle = hintFontStyle,
                        textFontStyle = textFontStyle,
                        editableLength = 9
                    )
                },
                isError = uiModel.ownerPhoneNumber.errorText != null,
                supportingText = uiModel.ownerPhoneNumber.errorText?.let { { Text(it) } },
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth(),
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = { onUserInteraction.invoke(CreateAdvertisementIntent.CreateAdvertisementButtonClicked) },
            modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth()
        ) {
            Text("Create the rental advertisement")
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}
