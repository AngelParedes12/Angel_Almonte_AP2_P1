package edu.ucne.angel_almonte_ap2_p1.presentantion.list

sealed interface listCervezaUiEvent {
    data class DescripcionChange(val value: String): listCervezaUiEvent
    data class MontoChange(val value: String): listCervezaUiEvent
    object Save: listCervezaUiEvent
    data class Delete(val id: Int): listCervezaUiEvent
    object New: listCervezaUiEvent
}