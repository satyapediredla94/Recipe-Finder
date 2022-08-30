package com.example.caloriecounter.presentation.recipe.recipe_detail

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.caloriecounter.model.recipenutrients.Nutrition


@Composable
fun RecipeImage(recipeNutrition: Nutrition) {
    AsyncImage(
        model = recipeNutrition.image, contentDescription = recipeNutrition.title,
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
        contentScale = ContentScale.FillWidth
    )
}
