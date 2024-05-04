package com.rendo.feature.rents.ui.composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rendo.feature.rents.domain.mvi.RentsIntent
import com.rendo.feature.rents.ui.OnUserInteraction
import com.rendo.feature.rents.ui.model.RentUiModel
import com.rendo.feature.rents.ui.model.RentsUiModel
import com.seiko.imageloader.ui.AutoSizeImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RentsContentComposable(
    rentsUiModel: RentsUiModel,
    onUserInteraction: OnUserInteraction
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val isRentInsSelected = selectedTabIndex == 0
    val titles = listOf("Rent Ins", "Rent Outs")
    Column {
        PrimaryTabRow(selectedTabIndex = selectedTabIndex) {
            titles.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = title) },
                )
            }
        }
        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(if (isRentInsSelected) rentsUiModel.rentIns else rentsUiModel.rentOuts) { rentUiModel ->
                RentComposable(rentUiModel, onUserInteraction)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun RentComposable(
    uiModel: RentUiModel,
    onUserInteraction: OnUserInteraction,
    modifier: Modifier = Modifier
) {
    var showDropdown by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(MaterialTheme.colorScheme.background)
            .clickable { onUserInteraction(RentsIntent.RentClicked(uiModel.id)) }
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row {
            AutoSizeImage(
                url = uiModel.imageUrl.orEmpty(),
                contentDescription = null,
                modifier = Modifier.clip(RoundedCornerShape(8.dp)).size(88.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = uiModel.name, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = uiModel.status.text, color = uiModel.status.color)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = uiModel.dates)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = uiModel.price, style = MaterialTheme.typography.titleMedium)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box {
                CompositionLocalProvider(LocalMinimumInteractiveComponentEnforcement provides false) {
                    IconButton(onClick = { showDropdown = true }) {
                        Icon(Icons.Default.MoreVert, null)
                    }
                }
                DropdownMenu(
                    expanded = showDropdown,
                    onDismissRequest = { showDropdown = false }
                ) {
                    uiModel.dropdownItems.forEach { dropdownItem ->
                        DropdownMenuItem(
                            text = { Text(dropdownItem.text) },
                            onClick = {
                                onUserInteraction(RentsIntent.DropdownItemClicked(uiModel.id, dropdownItem.action))
                                showDropdown = false
                            },
                            leadingIcon = {
                                Icon(
                                    dropdownItem.icon,
                                    contentDescription = null
                                )
                            })
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        if (uiModel.showAcceptanceButtons) {
            Row {
                Button(
                    onClick = { onUserInteraction(RentsIntent.AcceptButtonClicked(uiModel.id)) },
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text("Accept")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { onUserInteraction(RentsIntent.RejectButtonClicked(uiModel.id)) },
                    modifier = Modifier.weight(0.5f)
                ) {
                    Text("Reject")
                }
            }
        }
        Button(
            onClick = { onUserInteraction(RentsIntent.DialNumberButtonClicked(uiModel.id)) },
            modifier = Modifier.fillMaxWidth(),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
            colors = ButtonDefaults.outlinedButtonColors(),
        ) {
            Text("Dial a number")
        }
    }
}
