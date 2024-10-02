package com.sevban.list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sevban.common.streams.EventStream
import com.sevban.list.components.ProductListItem
import com.sevban.ui.theme.QlubTheme

@Composable
fun ListView(viewModel: State<List<ProductViewModel>>, eventStream: EventStream<ListEvent>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
        horizontalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.CenterHorizontally)
    ) {
        items(viewModel.value) {
            ProductListItem(
                product = it,
                modifier = Modifier,
                onClick = {
                    eventStream.notify(ListEvent.OnItemClick(it.id))
                }
            )
        }
    }
}