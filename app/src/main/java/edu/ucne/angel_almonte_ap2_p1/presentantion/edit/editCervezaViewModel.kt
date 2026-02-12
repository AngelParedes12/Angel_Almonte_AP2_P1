package edu.ucne.angel_almonte_ap2_p1.presentantion.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza
import edu.ucne.angel_almonte_ap2_p1.domain.usaCase.DeleteCervezaUseCase
import edu.ucne.angel_almonte_ap2_p1.domain.usaCase.GetCervezaUseCase
import edu.ucne.angel_almonte_ap2_p1.domain.usaCase.UpsertCervezaUseCase
import edu.ucne.angel_almonte_ap2_p1.domain.validation.validatePuntuacion
import edu.ucne.angel_almonte_ap2_p1.domain.validation.validateTexto
import edu.ucne.angel_almonte_ap2_p1.presentantion.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditCervezaViewModel @Inject constructor(
    private val getCervezaUseCase: GetCervezaUseCase,
    private val upsertCervezaUseCase: UpsertCervezaUseCase,
    private val deleteCervezaUseCase: DeleteCervezaUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val routeArgs = savedStateHandle.toRoute<Screen.Edit>()
    private val cervezaId: Int? = routeArgs.id

    private val _state = MutableStateFlow(EditCervezaUiState())
    val state: StateFlow<EditCervezaUiState> = _state.asStateFlow()

    init {
        loadCerveza(cervezaId)
    }

    fun onEvent(event: EditCervezaUiEvent) {
        when (event) {
            is EditCervezaUiEvent.Load -> loadCerveza(event.id)
            is EditCervezaUiEvent.NombreChange -> _state.update {
                it.copy(nombre = event.value, nombreError = null)
            }
            is EditCervezaUiEvent.MarcaChange -> _state.update {
                it.copy(marca = event.value, marcaError = null)
            }
            is EditCervezaUiEvent.PuntuacionChange -> _state.update {
                it.copy(puntuacion = event.value, puntuacionError = null)
            }
            EditCervezaUiEvent.Save -> onSave()
            EditCervezaUiEvent.Delete -> onDelete()
        }
    }

    private fun loadCerveza(id: Int?) {
        if (id == null || id == 0) {
            _state.update { it.copy(isNew = true, cervezaId = null) }
            return
        }

        viewModelScope.launch {
            val cerveza = getCervezaUseCase(id)
            if (cerveza != null) {
                _state.update {
                    it.copy(
                        isNew = false,
                        cervezaId = cerveza.cervezaId,
                        nombre = cerveza.nombre,
                        marca = cerveza.marca,
                        puntuacion = cerveza.puntuacion.toString()
                    )
                }
            } else {
                _state.update { it.copy(isNew = true, cervezaId = null) }
            }
        }
    }

    private fun onSave() {
        val nombreValid = validateTexto(state.value.nombre)
        val marcaValid = validateTexto(state.value.marca)
        val puntuacionValid = validatePuntuacion(state.value.puntuacion)

        if (!nombreValid.isValid || !marcaValid.isValid || !puntuacionValid.isValid) {
            _state.update {
                it.copy(
                    nombreError = nombreValid.error,
                    marcaError = marcaValid.error,
                    puntuacionError = puntuacionValid.error
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }

            val cerveza = Cerveza(
                cervezaId = state.value.cervezaId ?: 0,
                nombre = state.value.nombre,
                marca = state.value.marca,
                puntuacion = state.value.puntuacion.toInt()
            )

            val result = upsertCervezaUseCase(cerveza)

            result.onSuccess {
                _state.update {
                    it.copy(
                        isSaving = false,
                        saved = true,
                        isNew = false
                    )
                }
            }.onFailure {
                _state.update { it.copy(isSaving = false) }
            }
        }
    }

    private fun onDelete() {
        val id = state.value.cervezaId ?: return
        viewModelScope.launch {
            _state.update { it.copy(isDeleting = true) }
            deleteCervezaUseCase(id)
            _state.update { it.copy(isDeleting = false, deleted = true) }
        }
    }
}