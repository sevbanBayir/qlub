package com.sevban.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sevban.common.streams.EventStream

@Composable
fun ListView(viewModel: State<List<ProductViewModel>>, eventStream: EventStream<ListEvent>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
        horizontalArrangement = Arrangement.spacedBy(
            16.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        items(viewModel.value) {
            ListItem(
                headlineContent = { Text(it.title) },
                supportingContent = { Text(it.description) },
                trailingContent = { Text(it.price.toString()) },
                modifier = Modifier
                    .size(100.dp)
                    .clickable { eventStream.notify(ListEvent.OnItemClick(it.id)) }
                    .clip(RoundedCornerShape(8.dp))
            )
        }
    }
}

@Preview
@Composable
fun LoggedOutViewPreview() {
    val viewModel = remember {
        mutableStateOf(
            listOf(
                ProductViewModel(1, "title1", "description1", 1.2),
                ProductViewModel(2, "title2", "description2", 2.2),
                ProductViewModel(3, "title3", "description3", 3.2),
            )
        )
    }
    ListView(viewModel, EventStream())
}
