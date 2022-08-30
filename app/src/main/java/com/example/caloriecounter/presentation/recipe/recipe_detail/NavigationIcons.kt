package com.example.caloriecounter.presentation.recipe.recipe_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.navigation.NavHostController


@Composable
fun NavigationIcons(navController: NavHostController) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = {
            navController.navigateUp()
        }) {
            Image(
                imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
            )
        }
        IconButton(onClick = {}) {
            Image(
                imageVector = Icons.Default.Favorite, contentDescription = "Favorite",
                colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
            )
        }
    }
}