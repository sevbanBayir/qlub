package com.sevban.list

import com.sevban.common.extensions.DEFAULT_ZERO
import com.sevban.common.extensions.EMPTY

data class ProductViewModel(
    val id: Int = Int.DEFAULT_ZERO,
    val title: String = String.EMPTY,
    val description: String = String.EMPTY,
    val price: String = String.EMPTY,
    val imageUrl: String = String.EMPTY,
    val rating: String = String.EMPTY,
    val category: String = String.EMPTY,
    val reviewCount: String = String.EMPTY
)