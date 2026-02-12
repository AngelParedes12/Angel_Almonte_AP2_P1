package edu.ucne.angel_almonte_ap2_p1.presentantion.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.angel_almonte_ap2_p1.domain.usaCase.DeleteCervezaUseCase
import edu.ucne.angel_almonte_ap2_p1.domain.usaCase.GetCervezaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class listCervezaViewModel @Inject constructor(
    private val deleteCervezaUseCase: DeleteCervezaUseCase,
    private val getCervezaUseCase: GetCervezaUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(listCervezaUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAll().collect { list -> _state.update { it.copy(items = list) } }
        }
    }

    fun onEvent(event: listCervezaEvent) {
        when(event) {
            is listCervezaEvent.DescripcionChange -> _state.update { it.copy(descripcion = event.value) }
            is listCervezaEvent.MontoChange -> _state.update { it.copy(monto = event.value) }
            is listCervezaEvent.Delete -> viewModelScope.launch {
            }
            listCervezaEvent.Save -> viewModelScope.launch {
                val item = listCerveza(
                    listCervezaId = _state.value.listCervezaId,
                    descripcion = _state.value.descripcion,
                    monto = _state.value.monto.toDoubleOrNull() ?: 0.0
                )
                saveUC(item)
                _state.update { it.copy(isSaved = true) }
            }
            listCervezaEvent.New -> _state.update { it.copy(listCervezaId = null, descripcion = "", monto = "", isSaved = false) }
        }
    }
}