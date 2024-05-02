package com.rendo.feature.favorites.ui

import com.rendo.feature.favorites.domain.mvi.FavoritesIntent

typealias OnUserInteraction = (FavoritesIntent) -> Unit
