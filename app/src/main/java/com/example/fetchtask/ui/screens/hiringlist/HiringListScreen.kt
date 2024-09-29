package com.example.fetchtask.ui.screens.hiringlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.fetchtask.R
import com.example.fetchtask.data.model.HiringItem
import com.example.fetchtask.ui.appdesign.TopAppBar

//naming for better visualization
typealias OnRetryClick = () -> Unit



//state hoisting for more decoupled code
@Composable
fun HiringListScreen() {
    val viewModel: HiringListViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    when (uiState) {
        is HiringListUiState.Error -> {
            ErrorScreen(error = (uiState as HiringListUiState.Error).error!!) { viewModel.fetchHiringList() }
        }

        HiringListUiState.Loading -> {
            LoadingProgress()
        }

        is HiringListUiState.Success -> {
            HiringListContent(displayList = (uiState as HiringListUiState.Success).result)

        }
    }


}


//loading case
@Composable
fun LoadingProgress() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

//error screen with retry action
@Composable
fun ErrorScreen(error: Throwable, onRetryClick: OnRetryClick) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            text = stringResource(R.string.something_went_wrong, error.message.orEmpty()),
            textAlign = TextAlign.Center
        )

        Button(onClick = onRetryClick) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.retry),
                textAlign = TextAlign.Center
            )
        }

    }

}

//success screen without state, state is passed as param
@Composable
fun HiringListContent(
    displayList: Map<Int, List<HiringItem>>,
    modifier: Modifier = Modifier
) {

    Scaffold(modifier = modifier, topBar = {

        TopAppBar(
            titleRes = R.string.app_name
        )

    }) { padding ->
        HiringGroup(
            displayList,
            Modifier.padding(padding)
        )
    }
}

@Composable
fun HiringGroup(exploringList: Map<Int, List<HiringItem>>, modifier: Modifier) {


    LazyColumn(modifier = modifier) {
        exploringList.entries.forEach { entry ->
            item {
                HiringListGroupHeader(entry.key.toString())
            }
            item {
                HiringList(items = entry.value)
            }

        }
    }


}

@Composable
fun HiringListGroupHeader(title: String) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.LightGray,
        shadowElevation = 8.dp
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            text = stringResource(R.string.list_id, title),
            color = Color.DarkGray,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}

@Composable
fun HiringList(
    items: List<HiringItem>
) {
    repeat(items.size) { index ->
        HiringItemRow(
            modifier = Modifier.fillMaxWidth(),
            item = items[index]
        )
    }


}


//hiring item
@Composable
private fun HiringItemRow(
    modifier: Modifier = Modifier, item: HiringItem
) {
    Row(
        modifier = modifier
            .padding(top = 12.dp, bottom = 12.dp, start = 24.dp, end = 24.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = stringResource(R.string.id, item.id),
                color = Color.Gray,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.name, item.name.orEmpty()),
                color = Color.Gray,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}



//dummy data for preview
@Preview
@Composable
fun Preview() {
    HiringListContent(
        mapOf(
            1 to listOf(
                HiringItem(id = 10, listId = 15, name = "Android"),
                HiringItem(id = 10, listId = 15, name = "Android"),
                HiringItem(id = 10, listId = 15, name = "Android"),
                HiringItem(id = 10, listId = 15, name = "Android"),
                HiringItem(id = 10, listId = 15, name = "Android"),
                HiringItem(id = 10, listId = 15, name = "Android"),
                HiringItem(id = 10, listId = 15, name = "Android"),
                HiringItem(id = 10, listId = 15, name = "Android"),
                HiringItem(id = 10, listId = 15, name = "Android"),
                HiringItem(id = 10, listId = 15, name = "Android"),
                HiringItem(id = 10, listId = 15, name = "Android"),
                HiringItem(id = 10, listId = 15, name = "Android"),
                HiringItem(id = 10, listId = 15, name = "Android"),
            )
        )
    )
}
