package com.example.SWEFinalProject.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalCoilApi::class)
@Composable
fun CarDetailsScreen(
    navController: NavController,
    carId: Int,
    viewModel: CarViewModel = hiltViewModel()
) {
    val car = viewModel.carDetails.collectAsState()

    // Fetch car details when the screen loads
    viewModel.getCarDetails(carId)
    println(car.value)
    val showReserverationSuccessful = remember {
        mutableStateOf(false)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Car Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) {
        car.value?.let {
            if (!showReserverationSuccessful.value) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .padding(top = 100.dp)
                        .fillMaxSize()
                ) {
                    println(car.value?.make)
                    Text("Make: ${it.make}")
                    Text("Model: ${it.model}")
                    Text("Year: ${it.year}")
                    Text("Color: ${it.color}")
                    Text("Available: ${if (it.available) "Yes" else "No"}")

                    Image( // The Image component to load the image with the Coil library
                        painter = rememberImagePainter(data = it.imageUrl),
                        contentDescription = null,
                        modifier = Modifier.size(500.dp, 400.dp)
                    )
                    Button(
                        onClick = { viewModel.makeReservation(it.id)
                                  showReserverationSuccessful.value = true},
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {

                        Text(text = "Reserve")
                    }

                } ?: Text("Loading car details...")
            } else {
                ReservationScreen(showReserverationSuccessful)
            }

        }
    }
}


@Composable
fun ReservationScreen(show: MutableState<Boolean>) {
    var isUpdated by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(2000L)
        show.value = false
    }

    // UI content
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = if (isUpdated) "State Updated!" else "Reservation successful",
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center
        )
    }
}



