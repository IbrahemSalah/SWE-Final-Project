package com.example.SWEFinalProject.ui.screens.hiringlist

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