package com.example.caloriecounter.presentation.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
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
    recipeUIState.nutrition?.let {
        if (it.id != recipeId)
            viewModel.getRecipeWithNutrition(recipeId)
    } ?: viewModel.getRecipeWithNutrition(recipeId)


    val recipeNutrition = recipeUIState.nutrition
    Column(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()
    ) {
        if (recipeUIState.isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        }
        if (recipeNutrition != null) {
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
                model = recipeNutrition.image, contentDescription = recipeNutrition.title,
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f),
                contentScale = ContentScale.FillWidth
            )
            VerticalSpacer()
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = recipeNutrition.title ?: "",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                VerticalSpacer(modifier = Modifier.height(5.dp))
                Row {
                    Text(text = "Prep Time: ")
                    VerticalSpacer(modifier = Modifier.width(2.dp))
                    Image(
                        imageVector = Icons.Filled.Timer, contentDescription = "",
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                    )
                    VerticalSpacer(modifier = Modifier.width(4.dp))
                    Text(text = "${recipeNutrition.readyInMinutes} min")
                }
            }
            VerticalSpacer()
            RenderPagerState(recipeNutrition)
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