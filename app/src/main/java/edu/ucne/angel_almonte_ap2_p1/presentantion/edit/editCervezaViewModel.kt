package edu.ucne.angel_almonte_ap2_p1.presentantion.edit

import android.media.CamcorderProfile.getAll
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza
import edu.ucne.angel_almonte_ap2_p1.domain.usaCase.DeleteCervezaUseCase
import edu.ucne.angel_almonte_ap2_p1.domain.usaCase.GetCervezaUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class editCervezaViewModel @Inject constructor(
    private val deleteCervezaUseCase: DeleteCervezaUseCase,
    private val getCervezaUseCase: GetCervezaUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(EditCervezaUiState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getAll().collect { list -> _state.update { it.copy(items = list) } }
        }
    }

    fun onEvent(event: editCervezaUiEvent) {
        when(event) {
            is editCervezaUiEvent.DescripcionChange -> _state.update { it.copy(descripcion = event.value) }
            is editCervezaUiEvent.PuntuacionChange -> _state.update { it.copy(monto = event.value) }
            is editCervezaUiEvent.Delete -> viewModelScope.launch {

            }
            editCervezaUiEvent.Save -> viewModelScope.launch {
                val item = Cerveza(
                    cervezaId = _state.value.cervezaId,

                    descripcion = _state.value.descripcion,
                    monto = _state.value.monto.toDoubleOrNull() ?: 0.0
                )
                saveUC(item)
                _state.update { it.copy(isSaved = true) }
            }
            editCervezaUiEvent.New -> _state.update { it.copy(CervezaId = null, descripcion = "", monto = "", isSaved = false) }
        }
    }
}