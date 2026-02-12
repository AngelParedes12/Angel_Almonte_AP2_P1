package edu.ucne.angel_almonte_ap2_p1.presentantion.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.angel_almonte_ap2_p1.presentantion.edit.EditCervezaScreen
import edu.ucne.angel_almonte_ap2_p1.presentantion.list.ListCervezaScreen

@Composable
fun ExamenNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.List
    ) {
        composable<Screen.List> {
            ListCervezaScreen(
                onAdd = {
                    navController.navigate(Screen.Edit(null))
                },
                onEdit = { id ->
                    navController.navigate(Screen.Edit(id))
                }
            )
        }

        composable<Screen.Edit> {
            EditCervezaScreen(
                onBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}