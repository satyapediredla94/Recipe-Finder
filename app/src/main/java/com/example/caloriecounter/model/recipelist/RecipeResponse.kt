package com.example.caloriecounter.model.recipelist

import com.google.gson.annotations.SerializedName

data class RecipeResponse(
    val number: Int,
    val offset: Int,
    @SerializedName("results")
    val recipe: List<Recipe>,
    val totalResults: Int
)