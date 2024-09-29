package com.example.fetchtask.ui.screens.hiringlist

import com.example.fetchtask.data.model.HiringItem
import com.example.fetchtask.data.repository.Repository
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