package edu.ucne.angel_almonte_ap2_p1.presentantion.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun EditCervezaScreen(
    viewModel: EditCervezaViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.saved) {
        if (state.saved) {
            onBack()
        }
    }

    EditCervezaBody(
        state = state,
        onEvent = viewModel::onEvent,
        onBack = onBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditCervezaBody(
    state: EditCervezaUiState,
    onEvent: (EditCervezaUiEvent) -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (state.cervezaId == null) "Nueva Cerveza" else "Editar Cerveza") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Atrás")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(
                value = state.nombre,
                onValueChange = { onEvent(EditCervezaUiEvent.NombreChange(it)) },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.nombreError != null,
                supportingText = {
                    state.nombreError?.let { Text(it) }
                }
            )

            OutlinedTextField(
                value = state.marca,
                onValueChange = { onEvent(EditCervezaUiEvent.MarcaChange(it)) },
                label = { Text("Marca") },
                modifier = Modifier.fillMaxWidth(),
                isError = state.marcaError != null,
                supportingText = {
                    state.marcaError?.let { Text(it) }
                }
            )

            OutlinedTextField(
                value = state.puntuacion,
                onValueChange = { onEvent(EditCervezaUiEvent.PuntuacionChange(it)) },
                label = { Text("Puntuación (1-5)") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth(),
                isError = state.puntuacionError != null,
                supportingText = {
                    state.puntuacionError?.let { Text(it) }
                }
            )

            Button(
                onClick = { onEvent(EditCervezaUiEvent.Save) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !state.isSaving
            ) {
                if (state.isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                } else {
                    Text("Guardar")
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun EditCervezaScreenPreview() {
    MaterialTheme {
        val state = EditCervezaUiState(
            nombre = "Cerveza de Prueba",
            marca = "Marca X",
            puntuacion = "5"
        )
        EditCervezaBody(state, {}, {})
    }
}