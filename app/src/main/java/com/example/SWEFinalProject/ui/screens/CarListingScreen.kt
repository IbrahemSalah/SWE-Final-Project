package com.example.SWEFinalProject.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.SWEFinalProject.data.model.Car

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarListScreen(navController: NavController, viewModel: CarViewModel = hiltViewModel()) {
    val cars = viewModel.carList.collectAsState()

    // Fetch car data when the screen loads
   viewModel.getCars()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Car List") })
        }
    ) {

        LazyColumn(
            modifier = Modifier.padding(16.dp).padding(top = 100.dp).padding(bottom = 20.dp)
        ) {
            items(cars.value) { car ->
                CarItem(car, onClick = {
                    navController.navigate("carDetails/${car.id}")
                })
            }
        }
    }
}

@Composable
fun CarItem(car: Car, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick),

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Make: ${car.make}")
            Text("Model: ${car.model}")
            Text("Year: ${car.year}")
            Text("Color: ${car.color}")
        }
    }
}
