package com.example.caloriecounter.db

import androidx.room.TypeConverter
import com.example.caloriecounter.model.recipenutrients.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class WinePairingTypeConverter {
    @TypeConverter
    fun fromSource(value: String?): WinePairing {
        val type = object : TypeToken<WinePairing>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toSource(value: WinePairing): String {
        return Gson().toJson(value)
    }
}

class NutritionXTypeConverter {
    @TypeConverter
    fun fromSource(value: String?): NutritionX {
        val type = object : TypeToken<NutritionX>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toSource(value: NutritionX): String {
        return Gson().toJson(value)
    }
}

class MeasureTypeConverter {
    @TypeConverter
    fun fromSource(value: String?): Measures {
        val type = object : TypeToken<Measures>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toSource(value: Measures): String {
        return Gson().toJson(value)
    }
}

class ListTypeConverters {
    @TypeConverter
    fun toListFromString(value: String?): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
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
        val listType = object : TypeToken<List<ExtendedIngredient>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListToString(value: List<ExtendedIngredient>): String {
        return Gson().toJson(value)
    }
}

class ListAIConverters {
    @TypeConverter
    fun toListFromString(value: String?): List<AnalyzedInstructionsItem> {
        val listType = object : TypeToken<List<AnalyzedInstructionsItem>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromListToString(value: List<AnalyzedInstructionsItem>): String {
        return Gson().toJson(value)
    }
}
