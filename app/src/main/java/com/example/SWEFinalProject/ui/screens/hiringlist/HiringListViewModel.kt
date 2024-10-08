package com.example.SWEFinalProject.ui.screens.hiringlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.SWEFinalProject.data.model.HiringItem
import com.example.SWEFinalProject.data.repository.Repository
import com.example.SWEFinalProject.util.Result
import com.example.SWEFinalProject.util.asResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HiringListViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    private val viewModelState = MutableStateFlow<HiringListUiState>(HiringListUiState.Loading)

    //Immutable UI state exposed to the UI
    val uiState = viewModelState.stateIn(
        scope = viewModelScope,
        initialValue = viewModelState.value,
        started = SharingStarted.WhileSubscribed(5_000),
    )



    init {
        fetchHiringList()
    }


    fun fetchHiringList() {

        viewModelScope.launch {
            repository.fetchHiringList().asResult().collect { result ->
                when (result) {
                    is Result.Error -> {
                        viewModelState.value = HiringListUiState.Error(error = result.exception)
                    }

                    Result.Loading -> {
                        viewModelState.value = HiringListUiState.Loading
                    }

                    is Result.Success -> {
                        val displayList = prepareList(result.data)
                        viewModelState.value = HiringListUiState.Success(result = displayList)
                    }
                }
            }
        }
    }


    //format the input list
    fun prepareList(list: List<HiringItem>): Map<Int, List<HiringItem>> {
        //display criteria
        return list.filterNot { it.name.isNullOrBlank() }
            .sortedBy { it.listId }
            .sortedBy { it.name }
            .groupBy { it.listId }
    }
}


//ui sealed interface
sealed interface HiringListUiState {
    data object Loading : HiringListUiState
    data class Error(val error: Throwable?) : HiringListUiState
    data class Success(val result: Map<Int, List<HiringItem>>) : HiringListUiState

}


