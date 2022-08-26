package com.example.caloriecounter.presentation.recipe

import com.example.caloriecounter.model.recipenutrients.Nutrition

data class RecipeUIState(
    val nutrition: Nutrition? = null,
    val message: String? = null,
    val isLoading: Boolean = false
)
