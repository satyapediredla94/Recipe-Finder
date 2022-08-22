package com.example.caloriecounter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.caloriecounter.model.db.LocalIngredient
import com.example.caloriecounter.model.recipe.recipedata.RecipeData
import com.example.caloriecounter.model.recipelist.Recipe
import com.example.caloriecounter.repository.db.FoodDao

@Database(
    entities = [LocalIngredient::class, Recipe::class, RecipeData::class],
    version = 1
)
@TypeConverters(
    value = [AmountTypeConverter::class,
        ListTypeConverters::class,
        WinePairingTypeConverter::class,
        ListEIConverters::class,
        ListAIConverters::class]
)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}