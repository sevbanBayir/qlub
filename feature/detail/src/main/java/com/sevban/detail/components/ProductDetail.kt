package com.sevban.detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sevban.model.Product

@Composable
fun ProductDetailScreenContent(
    modifier: Modifier,
    product: Product,
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(product.thumbnail)
            .crossfade(true)
            .build()
    )
    Card(modifier = modifier.fillMaxSize()) {
        Image(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(top = 16.dp),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.Fit,
        )
        ProductDetails(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(),
            title = product.title,
            description = product.description,
            price = product.price,
            rate = product.rating,
        )
    }
}

@Composable
private fun ProductDetails(
    modifier: Modifier,
    title: String,
    description: String,
    price: Double,
    rate: Double,
) {
    Column(
        modifier = modifier,
    ) {
        ProductInfo(
            modifier = Modifier.weight(4f),
            title = title,
            description = description,
            rate = rate,
        )
        HorizontalDivider()
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier,
                text = "$$price",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun ProductInfo(
    modifier: Modifier,
    title: String,
    description: String,
    rate: Double,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                )
                Text(text = "$rate")
            }
        }
        Text(
            modifier = Modifier,
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = description
        )
    }
}