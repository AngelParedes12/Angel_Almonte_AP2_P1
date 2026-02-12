package edu.ucne.angel_almonte_ap2_p1.presentantion.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.angel_almonte_ap2_p1.domain.usaCase.DeleteCervezaUseCase
import edu.ucne.angel_almonte_ap2_p1.domain.usaCase.ObserveCervezasUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListCervezaViewModel @Inject constructor(
    private val observeCervezasUseCase: ObserveCervezasUseCase,
    private val deleteCervezaUseCase: DeleteCervezaUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListCervezaUiState(isLoading = true))
    val state: StateFlow<ListCervezaUiState> = _state.asStateFlow()

    init {
        loadCervezas()
    }

    fun onEvent(event: ListCervezaUiEvent) {
        when (event) {
            ListCervezaUiEvent.Load -> loadCervezas()
            is ListCervezaUiEvent.Delete -> onDelete(event.id)
            is ListCervezaUiEvent.ShowMessage -> _state.update { it.copy(message = event.message) }
            ListCervezaUiEvent.ClearMessage -> _state.update { it.copy(message = null) }
        }
    }

    private fun loadCervezas() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            observeCervezasUseCase().collectLatest { list ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        cervezas = list,
                        message = null
                    )
                }
            }
        }
    }

    private fun onDelete(id: Int) {
        viewModelScope.launch {
            deleteCervezaUseCase(id)
            onEvent(ListCervezaUiEvent.ShowMessage("Cerveza eliminada"))
        }
    }
}