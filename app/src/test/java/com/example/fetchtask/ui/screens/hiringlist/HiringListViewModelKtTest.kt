package com.example.fetchtask.ui.screens.hiringlist

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.fetchtask.data.model.HiringItem
import com.example.fetchtask.data.repository.Repository
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HiringListViewModelKtTest {


    @get:Rule
    val dispatcherRule = MainDispatcherRule()
    private lateinit var viewModel: HiringListViewModel
    private lateinit var repositoryFake: Repository

    @Before
    fun setUp() {
        repositoryFake = RepositoryFake()
        viewModel = HiringListViewModel(repositoryFake)
    }


    @Test
    fun `when sending different item with same list id, we got only one group`() {

        val hiringList = listOf(
            HiringItem(1, 10, "First"),
            HiringItem(1, 10, "First"),
            HiringItem(1, 10, "First"),
            HiringItem(1, 10, "First"),
        )


        val result = viewModel.prepareList(hiringList)


        assertThat(result.entries.size).isEqualTo(1)
    }

    @Test
    fun `when sending different item with two list id, we got two group`() {

        val hiringList = listOf(
            HiringItem(1, 10, "First"),
            HiringItem(1, 10, "First"),
            HiringItem(1, 10, "First"),
            HiringItem(1, 11, "First"),
        )


        val result = viewModel.prepareList(hiringList)


        assertThat(result.entries.size).isEqualTo(2)
    }

    @Test
    fun `when sending item with empty or null name, it removes it from the list`() {

        val hiringList = listOf(
            HiringItem(1, 10, "First"),
            HiringItem(1, 10, "First"),
            HiringItem(1, 10, null),
            HiringItem(1, 10, ""),
        )


        val result = viewModel.prepareList(hiringList)


        assertThat(result[10]?.size).isEqualTo(2)
    }

}