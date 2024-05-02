package com.rendo.feature.favorites.ui

import cafe.adriel.voyager.core.model.ScreenModel
import com.rendo.feature.favorites.domain.mvi.FavoritesStore

class FavoritesScreenModel(val store: FavoritesStore) : ScreenModel
