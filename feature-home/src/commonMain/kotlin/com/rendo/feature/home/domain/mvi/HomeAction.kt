package com.rendo.feature.home.domain.mvi

internal sealed class HomeAction {
    data object Init : HomeAction()
    data class FavoriteStateChanged(val id: String, val isInFavorites: Boolean) : HomeAction()
}
