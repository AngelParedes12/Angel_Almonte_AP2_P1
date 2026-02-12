package edu.ucne.angel_almonte_ap2_p1.presentantion.edit

sealed interface EditCervezaUiEvent {
    data class Load(val id: Int?) : EditCervezaUiEvent
    data class NombreChange(val value: String) : EditCervezaUiEvent
    data class MarcaChange(val value: String) : EditCervezaUiEvent
    data class PuntuacionChange(val value: String) : EditCervezaUiEvent
    data object Save : EditCervezaUiEvent
    data object Delete : EditCervezaUiEvent
}