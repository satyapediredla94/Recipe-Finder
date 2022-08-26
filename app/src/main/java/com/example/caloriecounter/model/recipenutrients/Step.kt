package com.example.caloriecounter.model.recipenutrients

data class Step(
    val equipment: List<Equipment>?,
    val ingredients: List<IngredientX>?,
    val length: Length?,
    val number: Int?,
    val step: String
)