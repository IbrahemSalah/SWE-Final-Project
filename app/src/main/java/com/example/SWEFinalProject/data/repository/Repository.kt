package com.example.SWEFinalProject.data.repository

import com.example.SWEFinalProject.data.model.HiringItem
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun fetchHiringList(): Flow<List<HiringItem>>
}