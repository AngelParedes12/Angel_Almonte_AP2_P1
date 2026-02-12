package edu.ucne.angel_almonte_ap2_p1.presentantion.list

data class ListState(
    val isLoading: Boolean = false,
    val items: List<> = emptyList(),
    val searchQuery: String = "",
    val totalCantidad: Int = 0,
    val totalDinero: Double = 0.0
)