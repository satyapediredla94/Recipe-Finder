package com.example.caloriecounter.model.recipenutrients

data class NutritionX(
    val caloricBreakdown: CaloricBreakdown,
    val flavonoids: List<Flavonoid>,
    val ingredients: List<Ingredient>,
    val nutrients: List<NutrientX>,
    val properties: List<Property>,
    val weightPerServing: WeightPerServing
)