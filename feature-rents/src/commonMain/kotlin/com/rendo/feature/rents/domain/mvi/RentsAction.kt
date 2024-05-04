package com.rendo.feature.rents.domain.mvi

sealed class RentsAction {
    data object Init : RentsAction()
    data class FavoriteStateChanged(val id: Long, val isInFavorites: Boolean) : RentsAction()
}
