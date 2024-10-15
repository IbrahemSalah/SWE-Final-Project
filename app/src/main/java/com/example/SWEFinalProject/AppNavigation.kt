package com.example.SWEFinalProject

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.SWEFinalProject.ui.screens.CarDetailsScreen
import com.example.SWEFinalProject.ui.screens.CarListScreen
import com.example.SWEFinalProject.ui.screens.CarViewModel

@Composable
fun AppNavigation(navController: NavHostController) {
    val carViewModel:CarViewModel = hiltViewModel()
    NavHost(navController, startDestination = "carList") {
        composable("carList") {
            CarListScreen(navController, carViewModel)
        }
        composable("carDetails/{carId}") { backStackEntry ->
            val carId = backStackEntry.arguments?.getString("carId")?.toIntOrNull()
            carId?.let {

                CarDetailsScreen(navController, it, carViewModel)
            }
        }
    }
}
