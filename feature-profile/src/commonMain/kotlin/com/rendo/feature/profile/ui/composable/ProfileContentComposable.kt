package com.rendo.feature.profile.ui.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FormatListBulleted
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.rendo.core.theme.LocalThemeIsDark
import com.rendo.feature.profile.domain.mvi.ProfileIntent
import com.rendo.feature.profile.ui.OnUserInteraction
import com.rendo.feature.profile.ui.model.ProfileUiModel
import com.seiko.imageloader.ui.AutoSizeImage
import org.jetbrains.compose.resources.painterResource
import rendo.feature_profile.generated.resources.Res
import rendo.feature_profile.generated.resources.ill_user_placeholder

@Composable
internal fun ProfileContentComposable(
    uiModel: ProfileUiModel,
    onUserInteraction: OnUserInteraction
) {
    Column {
        ProfileInfoRow(
            name = (uiModel as? ProfileUiModel.Authorized)?.name,
            imageUrl = (uiModel as? ProfileUiModel.Authorized)?.imageUrl,
            onUserInteraction = onUserInteraction,
        )
        when (uiModel) {
            is ProfileUiModel.Authorized -> {
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .clickable { onUserInteraction(ProfileIntent.AdvertisementsButtonClicked) }
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.background)
                        .padding(16.dp),
                ) {
                    Icon(Icons.AutoMirrored.Filled.FormatListBulleted, null)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text("My advertisements")
                }
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { onUserInteraction(ProfileIntent.SignOutButtonClicked) },
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                ) {
                    Text(text = "Sign out")
                }
            }

            is ProfileUiModel.Unauthorized -> {
                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = { onUserInteraction(ProfileIntent.SignInButtonClicked) },
                    modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                ) {
                    Text(text = "Sign in")
                }
            }
        }
    }
}

@Composable
private fun ProfileInfoRow(name: String?, imageUrl: String?, onUserInteraction: OnUserInteraction) {
    val isDarkMode by LocalThemeIsDark.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background, RoundedCornerShape(8.dp))
            .padding(16.dp),
    ) {
        AutoSizeImage(
            url = imageUrl.orEmpty(),
            placeholderPainter = { painterResource(Res.drawable.ill_user_placeholder) },
            errorPainter = { painterResource(Res.drawable.ill_user_placeholder) },
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .border(1.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
                .size(80.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        if (name != null) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleLarge,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(8.dp))
        IconButton(onClick = { onUserInteraction.invoke(ProfileIntent.UiModeButtonClicked(isDarkMode)) }) {
            val icon = if (isDarkMode) {
                Icons.Filled.DarkMode
            } else {
                Icons.Filled.LightMode
            }
            Icon(icon, null)
        }
    }
}
