package com.example.SWEFinalProject.ui.screens.hiringlist

import com.example.SWEFinalProject.data.model.HiringItem
import com.example.SWEFinalProject.data.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class RepositoryFake : Repository {
    override fun fetchHiringList(): Flow<List<HiringItem>> = flow {
        emit(
            listOf(
                HiringItem(1, 10, "First"),
                HiringItem(1, 10, "First"),
                HiringItem(1, 10, "First"),
                HiringItem(1, 10, "First"),
                HiringItem(1, 10, "First"),
                HiringItem(1, 10, "First")
            )
        )
    }
}