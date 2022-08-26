package com.example.caloriecounter.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.caloriecounter.model.recipelist.Recipe
import com.example.caloriecounter.model.recipenutrients.Nutrition
import com.example.caloriecounter.repository.db.FoodDao

@Database(
    entities = [Recipe::class, Nutrition::class],
    version = 1
)
@TypeConverters(
    value = [
        ListTypeConverters::class,
        WinePairingTypeConverter::class,
        ListEIConverters::class,
        ListAIConverters::class,
        NutritionXTypeConverter::class]
)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
}