package com.example.caloriecounter.presentation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun VerticalSpacer(modifier: Modifier = Modifier.height(10.dp)) {
    Spacer(modifier = modifier)
}

@Composable
fun HorizontalSpacer(modifier: Modifier = Modifier.width(10.dp)) {
    Spacer(modifier = modifier)
}