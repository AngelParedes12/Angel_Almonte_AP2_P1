package edu.ucne.angel_almonte_ap2_p1.presentantion.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object List : Screen

    @Serializable
    data class Edit(val id: Int?) : Screen
}