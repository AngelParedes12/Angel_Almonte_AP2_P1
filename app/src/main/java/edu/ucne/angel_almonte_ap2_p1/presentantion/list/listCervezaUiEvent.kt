package edu.ucne.angel_almonte_ap2_p1.presentantion.list

sealed class ListCervezaUiEvent {
    object Load : ListCervezaUiEvent()
    data class Delete(val id: Int) : ListCervezaUiEvent()
    data class ShowMessage(val message: String) : ListCervezaUiEvent()
    object ClearMessage : ListCervezaUiEvent()
}