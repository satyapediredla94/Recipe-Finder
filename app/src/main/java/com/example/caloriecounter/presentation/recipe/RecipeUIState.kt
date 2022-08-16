package com.example.caloriecounter.presentation.recipe

import com.example.caloriecounter.model.ingredients.Ingredient
import com.example.caloriecounter.model.recipe.RecipeData

data class RecipeUIState(
    val recipe: RecipeData? = null,
    val message: String? = null,
    val ingredients: List<Ingredient> = emptyList(),
    val isLoading: Boolean = false
)
