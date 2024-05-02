package com.rendo.feature.favorites.domain.mvi

import com.arkivanov.mvikotlin.core.store.Store

typealias FavoritesStore = Store<FavoritesIntent, FavoritesState, FavoritesLabel>
