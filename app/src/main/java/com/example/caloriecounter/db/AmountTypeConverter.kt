package com.example.caloriecounter.db

import androidx.room.TypeConverter
import com.example.caloriecounter.model.ingredients.Amount
import com.example.caloriecounter.model.ingredients.Metric
import com.example.caloriecounter.model.ingredients.Us
import com.example.caloriecounter.model.recipe.ExtendedIngredient
import com.example.caloriecounter.model.recipe.WinePairing
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject

class AmountTypeConverter {
    @TypeConverter
    fun fromSource(value: String?): Amount {
        val type = object : TypeToken<Amount>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun toSource(value: Amount): String {
        return Gson().toJson(value)
    }
}

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
