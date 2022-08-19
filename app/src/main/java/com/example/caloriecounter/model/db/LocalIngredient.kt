package com.example.caloriecounter.model.db

import androidx.room.Entity
import androidx.room.TypeConverters
import com.example.caloriecounter.db.AmountTypeConverter
import com.example.caloriecounter.model.ingredients.Amount

@Entity(primaryKeys = ["recipeId", "name"])
data class LocalIngredient(
    val recipeId: Int,
    @TypeConverters(AmountTypeConverter::class)
    val amount: Amount,
    val image: String?,
    val name: String
)
