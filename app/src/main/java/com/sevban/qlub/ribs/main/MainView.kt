package com.sevban.qlub.ribs.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainView(childContent: MainRouter.ChildContent) {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        childContent.fullScreenSlot.value.invoke()
    }
}

@Preview
@Composable
fun MainViewPreview() {
    MainView(MainRouter.ChildContent())
}
