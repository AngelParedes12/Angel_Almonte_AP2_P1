package edu.ucne.angel_almonte_ap2_p1.presentantion.edit

import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza

data class EditCervezaUiState(
    val isLoading: Boolean = false,
    val items: List<Cerveza> = emptyList(),
    val searchQuery: String = "",
    val totalCantidad: Int = 0,

)