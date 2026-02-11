package edu.ucne.angel_almonte_ap2_p1.presentantion.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

//@Composable
//fun ExamenNavHost(
//    navController: NavHostController = rememberNavController()
//) {
//    NavHost(
//        navController = navController,
//        startDestination = Screen.listScreen.route
//    ) {
//        composable<Screen.listScreen> {
//            ListScreen(
//                onAddTask = {
//                    navController.navigate(Screen.editScreen)
//                },
//                onEditTask = { id ->
//
//                }
//            )
//        }
//
//        composable<Screen.editScreen> {
//            EditScreen(
//                onBack = {
//                    navController.navigateUp()
//                }
//            )
//        }
//    }
//}