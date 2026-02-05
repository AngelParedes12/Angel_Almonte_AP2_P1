package edu.ucne.angel_almonte_ap2_p1.presentantion.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {
    @Serializable
    data object listScreen : Screen

    @Serializable
    data object editScreen : Screen
}