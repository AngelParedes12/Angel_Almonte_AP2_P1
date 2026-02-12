package edu.ucne.angel_almonte_ap2_p1.presentantion.list

import androidx.compose.material.icons.Icons
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun listCervezaScreen(
    viewModel: listCervezaViewModel = hiltViewModel(),
    onAdd: () -> Unit,
    onEdit: (Int) -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAdd) { Icon(Icons.Default.Add, "Agregar") }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            if (state.items.isEmpty()) {
                Text("No hay registros", modifier = Modifier.align(Alignment.CenterHorizontally).padding(16.dp))
            }
            LazyColumn {
                items(state.items) { item ->
                    ListItem(
                        headlineContent = { Text(item.descripcion) },
                        supportingContent = { Text("Monto: ${item.monto}") },
                        trailingContent = {
                            IconButton(onClick = { /* delete logic */ }) {
                                Icon(Icons.Default.Delete, "Borrar")
                            }
                        },
                        modifier = Modifier.clickable { item.listCervezaId?.let { onEdit(it) } }
                    )
                    HorizontalDivider()
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun listCervezaListPreview() {
    val list = listOf(
        listCerveza(1, "Ejemplo 1", 100.0),
        listCerveza(2, "Ejemplo 2", 200.0)
    )
    MaterialTheme {
        Column {
            list.forEach {
                Text(text = "${it.descripcion} - ${it.monto}")
                HorizontalDivider()
            }
        }
    }
}