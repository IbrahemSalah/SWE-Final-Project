package com.example.fetchtask.data.repository

import com.example.fetchtask.data.model.HiringItem
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchHiringList(): Flow<List<HiringItem>>
}