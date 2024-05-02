package com.rendo.feature.home.domain.mvi

sealed class HomeAction {
    data object Init : HomeAction()
    data class FavoriteStateChanged(val id: Long, val isInFavorites: Boolean) : HomeAction()
}
