package com.example.caloriecounter.presentation.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.caloriecounter.presentation.MainViewModel

@Composable
fun RecipeDetail(
    recipeId: Int,
    viewModel: MainViewModel,
    navController: NavHostController
) {
    val recipeUIState = viewModel.recipeUIState

    //Getting recipe details from repo based on the recipe ID
    recipeUIState.recipe?.let {
        if (it.id != recipeId)
            viewModel.getRecipeById(recipeId)
    } ?: viewModel.getRecipeById(recipeId)

    //If Ingredients are empty, get the ingredients from repo
    if (recipeUIState.ingredients.isEmpty()) {
        viewModel.getIngredientsById(recipeId)
    }
    val recipe = recipeUIState.recipe
    val ingredients = recipeUIState.ingredients
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        if (recipeUIState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        if (recipe != null) {
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
            AsyncImage(
                model = recipe.image, contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.4f),
                contentScale = ContentScale.FillWidth
            )
            VerticalSpacer()
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = recipe.title ?: "",
                    fontSize = 16.sp,
                )
                Row {
                    Image(
                        imageVector = Icons.Filled.Timer, contentDescription = "",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = "${recipe.readyInMinutes} min")
                }
            }
            VerticalSpacer()
            RenderPagerState(recipe, ingredients)
        }
    }
}

@Composable
fun VerticalSpacer(modifier: Modifier = Modifier.height(10.dp)) {
    Spacer(modifier = modifier)
}

@Composable
fun HorizontalSpacer(modifier: Modifier = Modifier.width(10.dp)) {
    Spacer(modifier = modifier)
}