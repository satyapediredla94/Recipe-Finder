package com.example.caloriecounter.presentation.recipe.recipe_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caloriecounter.model.recipenutrients.Nutrition
import com.example.caloriecounter.presentation.VerticalSpacer


@Composable
fun TitleAndPrepTime(recipeNutrition: Nutrition) {
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
}