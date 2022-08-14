package com.example.caloriecounter.presentation.recipe

import android.media.Rating
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.caloriecounter.presentation.MainViewModel

@Composable
fun RecipeDetail(
    recipeId: Int,
    viewModel: MainViewModel
) {
    val recipeUIState = viewModel.recipeUIState
    viewModel.getRecipeById(recipeId)
    val recipe = recipeUIState.recipe
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        if (recipeUIState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        if (recipe != null) {
            Row(horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()) {
                IconButton(onClick = {}) {
                    Image(imageVector = Icons.Default.ArrowBack, contentDescription = "Back",
                    colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary))
                }
                IconButton(onClick = {}) {
                    Image(imageVector = Icons.Default.Favorite, contentDescription = "Favorite",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary))
                }
            }
            AsyncImage(
                model = recipe.image, contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                contentScale = ContentScale.FillWidth
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = recipe.title)
                Row {
//                    Image(imageVector = Icons.Default, contentDescription = )
                    Text(text = recipe.readyInMinutes.toString())
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }
        }
    }
}