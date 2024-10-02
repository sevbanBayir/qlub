package com.sevban.list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.sevban.list.ProductViewModel

@Composable
fun ProductListItem(
    product: ProductViewModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(product.imageUrl)
            .crossfade(true)
            .build()
    )
    ElevatedCard(
        modifier = modifier.heightIn(min = 225.dp),
        onClick = onClick,
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
        )
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .align(CenterHorizontally),
                contentScale = ContentScale.Inside
            )
            Text(
                text = product.title,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = product.category,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraLight
            )
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = CenterVertically,
            ) {
                Icon(
                    modifier = Modifier
                        .size(18.dp)
                        .align(CenterVertically),
                    imageVector = Icons.Outlined.Star,
                    contentDescription = null,
                )
                Text(
                    modifier = Modifier.align(CenterVertically),
                    text = "${product.rating} (${product.reviewCount})",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "$${product.price}",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}