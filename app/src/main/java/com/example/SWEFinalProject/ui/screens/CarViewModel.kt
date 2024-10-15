package com.example.SWEFinalProject.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.SWEFinalProject.data.model.Car
import com.example.SWEFinalProject.data.repository.CarRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarViewModel @Inject constructor(private val carRepository: CarRepository) : ViewModel() {

    private val _carList = MutableStateFlow<List<Car>>(emptyList())
    val carList: StateFlow<List<Car>> = _carList

    private val _carDetails = MutableStateFlow<Car?>(null)
    val carDetails: StateFlow<Car?> = _carDetails

    fun getCars() {
            viewModelScope.launch {
            val cars = carRepository.getCars().content
            _carList.value = cars
        }
    }

    fun getCarDetails(id: Int) {
        viewModelScope.launch {
            println("id:"+id)
            println(_carList.value)
            val car = _carList.value.filter { it.id == id }.first()
            println(car)
            _carDetails.value = car
        }
    }

    fun makeReservation(id: Int) {

    }
}
