package com.example.caloriecounter.model.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.caloriecounter.db.ListEIConverters
import com.example.caloriecounter.db.ListTypeConverters
import com.example.caloriecounter.db.WinePairingTypeConverter

@Entity
data class RecipeData(
    val aggregateLikes: Int,
    @TypeConverters(ListTypeConverters::class)
    val analyzedInstructions: List<String>,
    val cheap: Boolean,
    val creditsText: String,
    @TypeConverters(ListTypeConverters::class)
    val cuisines: List<String>,
    val dairyFree: Boolean,
    @TypeConverters(ListTypeConverters::class)
    val diets: List<String>,
    @TypeConverters(ListTypeConverters::class)
    val dishTypes: List<String>,
    @TypeConverters(ListEIConverters::class)
    val extendedIngredients: List<ExtendedIngredient>,
    val gaps: String,
    val glutenFree: Boolean,
    val healthScore: Double,
    @PrimaryKey(autoGenerate = false) val id: Int,
    val image: String,
    val imageType: String,
    val instructions: String,
    val ketogenic: Boolean,
    val license: String,
    val lowFodmap: Boolean,
    @TypeConverters(ListTypeConverters::class)
    val occasions: List<String>,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int,
    val sourceName: String,
    val sourceUrl: String,
    val spoonacularScore: Double,
    val spoonacularSourceUrl: String,
    val summary: String,
    val sustainable: Boolean,
    val title: String,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val weightWatcherSmartPoints: Int,
    val whole30: Boolean,
    @TypeConverters(WinePairingTypeConverter::class)
    val winePairing: WinePairing
)