package com.example.caloriecounter.db

import androidx.room.TypeConverter
import com.example.caloriecounter.model.recipe.ExtendedIngredient
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class ListTypeConverters {
    @TypeConverter
    fun toListFromString(value: String?): List<String> {
        val listType = object: TypeToken<List<String>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListToString(value: List<String>): String {
        return Gson().toJson(value)
    }
}

class ListEIConverters {
    @TypeConverter
    fun toListFromString(value: String?): List<ExtendedIngredient> {
        val listType = object: TypeToken<List<ExtendedIngredient>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListToString(value: List<ExtendedIngredient>): String {
        return Gson().toJson(value)
    }
}