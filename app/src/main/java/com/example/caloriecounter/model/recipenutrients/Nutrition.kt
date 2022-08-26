package com.example.caloriecounter.model.recipenutrients

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.caloriecounter.db.*

@Entity
data class Nutrition(
    val aggregateLikes: Int?,
    @TypeConverters(ListAIConverters::class)
    val analyzedInstructions: List<AnalyzedInstructionsItem>?,
    val cheap: Boolean?,
    val cookingMinutes: Int?,
    val creditsText: String?,
    @TypeConverters(ListTypeConverters::class)
    val cuisines: List<String>?,
    val dairyFree: Boolean?,
    @TypeConverters(ListTypeConverters::class)
    val dishTypes: List<String>?,
    @TypeConverters(ListEIConverters::class)
    val extendedIngredients: List<ExtendedIngredient>?,
    val gaps: String?,
    val glutenFree: Boolean?,
    val healthScore: Int?,
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String?,
    val imageType: String?,
    val instructions: String?,
    val license: String?,
    val lowFodmap: Boolean?,
    @TypeConverters(NutritionXTypeConverter::class)
    val nutrition: NutritionX?,
    @TypeConverters(ListTypeConverters::class)
    val occasions: List<String>?,
    val originalId: Int?,
    val preparationMinutes: Int?,
    val pricePerServing: Double?,
    val readyInMinutes: Int?,
    val servings: Int?,
    val sourceName: String?,
    val sourceUrl: String?,
    val spoonacularSourceUrl: String?,
    val summary: String?,
    val sustainable: Boolean?,
    val title: String?,
    val vegan: Boolean?,
    val vegetarian: Boolean?,
    val veryHealthy: Boolean?,
    val veryPopular: Boolean?,
    val weightWatcherSmartPoints: Int?,
    @TypeConverters(WinePairingTypeConverter::class)
    val winePairing: WinePairing?
)