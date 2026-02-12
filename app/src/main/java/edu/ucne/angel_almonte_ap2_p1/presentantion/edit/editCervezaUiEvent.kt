package edu.ucne.angel_almonte_ap2_p1.presentantion.edit

sealed interface editCervezaUiEvent {
    data class DescripcionChange(val value: String): editCervezaUiEvent
    data class MontoChange(val value: String): editCervezaUiEvent
    object Save: editCervezaUiEvent
    data class Delete(val id: Int): editCervezaUiEvent
    object New: editCervezaUiEvent
}