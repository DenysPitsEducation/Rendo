package com.rendo.feature.create.advertisement.domain.model

import org.jetbrains.compose.resources.StringResource

internal data class InputDomainModel(
    val text: String,
    val errorText: StringResource?,
)
