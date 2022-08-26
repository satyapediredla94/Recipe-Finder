package com.example.caloriecounter.model.recipenutrients

data class Ingredient(
    val amount: Double,
    val id: Int,
    val name: String,
    val nutrients: List<NutrientX>,
    val unit: String
)