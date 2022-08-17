package com.example.caloriecounter.db

import androidx.room.TypeConverter
import com.example.caloriecounter.model.recipe.ExtendedIngredient
import com.example.caloriecounter.model.recipe.Measures
import org.json.JSONObject

class AnalyzedInstructionsTypeConverter {
    @TypeConverter
    fun fromSource(ingredient: ExtendedIngredient): String {
        return JSONObject().apply {
            put("aisle", ingredient.aisle)
            put("amount", ingredient.amount)
            put("consitency", ingredient.consitency)
            put("id", ingredient.id)
            put("image", ingredient.image)
            put("measures", ingredient.measures)
            put("meta", ingredient.meta)
            put("name", ingredient.name)
            put("original", ingredient.original)
            put("originalName", ingredient.originalName)
            put("unit", ingredient.unit)
        }.toString()
    }

    @TypeConverter
    fun toSource(ingredient: String): ExtendedIngredient {
        val json = JSONObject(ingredient)
        return ExtendedIngredient(
            aisle = json.getString("aisle"),
            amount = json.getDouble("amount"),
            consitency = json.getString("consitency"),
            id = json.getInt("id"),
            image = json.getString("image"),
            measures = json.get("measures") as Measures,
            meta = json.get("meta") as List<String>,
            name = json.getString("name"),
            original = json.getString("original"),
            originalName = json.getString("originalName"),
            unit = json.getString("unit")
        )
    }
}