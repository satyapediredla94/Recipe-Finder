package com.example.caloriecounter.presentation.recipe

import androidx.compose.foundation.layout.height
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.caloriecounter.model.recipe.recipedata.AnalyzedInstruction
import com.example.caloriecounter.model.recipe.recipedata.RecipeData

@Composable
fun AnalyzedInstruction(recipe: RecipeData) {
    recipe.analyzedInstructions?.let { analyzedInstList ->
        analyzedInstList.forEach {
            it.steps.forEach { step ->
                for (instruction in step.step.split(";")) {
                    Card {
                        Text(text = instruction)
                        VerticalSpacer(Modifier.height(3.dp))
                    }
                }
            }
        }
    }
}