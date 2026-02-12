package edu.ucne.angel_almonte_ap2_p1.presentantion.list

import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza

data class ListCervezaUiState(
    val isLoading: Boolean = false,
    val cervezas: List<Cerveza> = emptyList(),
    val message: String? = null
)