package edu.ucne.angel_almonte_ap2_p1.presentantion.list

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import okhttp3.internal.concurrent.Task
import androidx.hilt.navigation.compose.hiltViewModel


//@Composable
//fun listScreen (
//    viewModel: listViewModel = hiltViewModel(),
//    onAddTask: () -> Unit
//)
//
//@Preview(showBackground = true)
//@Composable
//private fun TaskListBodyPreview() {
//    MaterialTheme {
//        val state = TaskListUiState(
//            isLoading = false,
//            tasks = listOf(
//                Task(tareaId = 1, descripcion = "Tarea urgente", tiempo = 30),
//                Task(tareaId = 2, descripcion = "Tarea normal", tiempo = 45)
//            )
//        )
//        TaskListBody(state, {}, {})
//    }
//}