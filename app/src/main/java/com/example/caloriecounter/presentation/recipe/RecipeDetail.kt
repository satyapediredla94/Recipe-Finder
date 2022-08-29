package com.example.caloriecounter.presentation.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.caloriecounter.presentation.MainViewModel
import com.example.caloriecounter.presentation.VerticalSpacer
import com.example.caloriecounter.presentation.recipe.recipe_detail.NavigationIcons
import com.example.caloriecounter.presentation.recipe.recipe_detail.RecipeImage
import com.example.caloriecounter.presentation.recipe.recipe_detail.TitleAndPrepTime

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
            NavigationIcons(navController)
            RecipeImage(recipeNutrition)
            VerticalSpacer()
            TitleAndPrepTime(recipeNutrition)
            VerticalSpacer()
            RenderRecipePaging(
                recipeUIState,
                recipeNutrition,
                navController,
                modifier = Modifier.fillMaxHeight()
            )
        }
    }
}