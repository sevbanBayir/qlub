package com.sevban.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sevban.model.Product

@Composable
fun DetailView(viewModel: State<Product>) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            Text(viewModel.value.title, style = MaterialTheme.typography.titleLarge)
            Text(viewModel.value.description, style = MaterialTheme.typography.bodyMedium)
            Text(
                viewModel.value.price.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                viewModel.value.discountPercentage.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                viewModel.value.rating.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                viewModel.value.stock.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}