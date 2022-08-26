package com.example.caloriecounter.model.recipenutrients

data class NutrientX(
    val amount: Double,
    val name: String,
    val percentOfDailyNeeds: Double,
    val unit: String
)