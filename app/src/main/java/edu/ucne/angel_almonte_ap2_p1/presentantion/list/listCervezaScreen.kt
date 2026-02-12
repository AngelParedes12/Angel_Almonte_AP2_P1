package edu.ucne.angel_almonte_ap2_p1.presentantion.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import edu.ucne.angel_almonte_ap2_p1.domain.model.Cerveza

@Composable
fun ListCervezaScreen(
    viewModel: ListCervezaViewModel = hiltViewModel(),
    onAdd: () -> Unit,
    onEdit: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ListCervezaBody(
        state = state,
        onEvent = viewModel::onEvent,
        onAdd = onAdd,
        onEdit = onEdit
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCervezaBody(
    state: ListCervezaUiState,
    onEvent: (ListCervezaUiEvent) -> Unit,
    onAdd: () -> Unit,
    onEdit: (Int) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(state.message) {
        state.message?.let { message ->
            snackbarHostState.showSnackbar(message)
            onEvent(ListCervezaUiEvent.ClearMessage)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Cerveza")
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            } else if (state.cervezas.isEmpty()) {
                Text(
                    text = "No hay cervezas registradas",
                    modifier = Modifier.align(Alignment.Center),
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(
                        items = state.cervezas,
                        key = { it.cervezaId ?: 0 }
                    ) { cerveza ->
                        CervezaItem(
                            cerveza = cerveza,
                            onDelete = {
                                onEvent(ListCervezaUiEvent.Delete(cerveza.cervezaId ?: 0))
                            },
                            onClick = {
                                cerveza.cervezaId?.let { onEdit(it) }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CervezaItem(
    cerveza: Cerveza,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = cerveza.nombre,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Marca: ${cerveza.marca}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = "Puntos: ${cerveza.puntuacion}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListCervezaScreenPreview() {
    MaterialTheme {
        val state = ListCervezaUiState(
            isLoading = false,
            cervezas = listOf(
                Cerveza(1, "Presidente", "CND", 5),
                Cerveza(2, "Corona", "Modelo", 4),
                Cerveza(3, "Modelo Negra", "Modelo", 5)
            )
        )
        ListCervezaBody(state, {}, {}, {})
    }
}