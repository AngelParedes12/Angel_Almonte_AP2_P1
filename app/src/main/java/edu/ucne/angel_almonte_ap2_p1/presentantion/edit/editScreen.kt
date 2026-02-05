package edu.ucne.angel_almonte_ap2_p1.presentantion.edit

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import okhttp3.internal.concurrent.Task

@Composable
fun editScreen(
    viewModel: TaskEditViewModel = hiltViewModel(),
    onBack: () -> Unit
)

@Preview(showBackground = true)
@Composable
private fun TaskListBodyPreview() {
    MaterialTheme {
        val state = TaskListUiState(
            isLoading = false,
            tasks = listOf(
                Task(tareaId = 1, descripcion = "Tarea urgente", tiempo = 30),
                Task(tareaId = 2, descripcion = "Tarea normal", tiempo = 45)
            )
        )
        TaskListBody(state, {}, {})
    }
}