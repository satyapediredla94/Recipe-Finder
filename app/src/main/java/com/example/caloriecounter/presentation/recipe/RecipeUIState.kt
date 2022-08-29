package com.example.caloriecounter.presentation.recipe

import com.example.caloriecounter.model.SimilarRecipe
import com.example.caloriecounter.model.recipenutrients.Nutrition

data class RecipeUIState(
    val nutrition: Nutrition? = null,
    val message: String? = null,
    val similarRecipe: List<SimilarRecipe>? = emptyList(),
    val isLoading: Boolean = false
)
