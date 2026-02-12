package edu.ucne.angel_almonte_ap2_p1.presentantion.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@ExperimentalMaterial3Api
@Composable
fun editCervezaScreen(
    viewModel: editCervezaViewModel= hiltViewModel(),
    onBack: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(state.isSaved) {
        if (state.isSaved) onBack()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Registro de Cerveza") })
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
                value = state.marca,
                onValueChange = { viewModel.onEvent(editCervezaUiEvent.MarcaChange(it)) },
                label = { Text("Marca") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.puntuacion,
                onValueChange = { viewModel.onEvent(editCervezaUIEvent.puntoChange(it)) },
                label = { Text("Puntuacion") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = state.items,
                onValueChange = {viewModel.onEvent(editCervezaUiEvent.CervezaChange(it))},
                label = {Text("Cervezas")},
                keyboardOptions = KeyboardOptions(keyboardType = keyboarType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Button(
                onClick = { viewModel.onEvent(ediCervezaEvent.Save) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun ediCervezaEditPreview() {
    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            OutlinedTextField(value = "Ejemplo", onValueChange = {}, label = { Text("Descripción") })
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(value = "100.0", onValueChange = {}, label = { Text("Monto") })
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {}, modifier = Modifier.fillMaxWidth()) { Text("Guardar") }
        }
    }
}