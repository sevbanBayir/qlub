package com.sevban.detail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import com.sevban.detail.components.ProductDetailScreenContent
import com.sevban.model.Product

@Composable
fun DetailView(viewModel: State<Product>) {
    ProductDetailScreenContent(
        modifier = Modifier,
        product = viewModel.value
    )
}