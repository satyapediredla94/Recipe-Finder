package com.example.caloriecounter.presentation.search

import com.example.caloriecounter.model.recipelist.Recipe

data class FoodUIState(
    var recipes: List<Recipe> = emptyList(),
    var message: String = "",
    val isLoading: Boolean = false
)