package com.example.caloriecounter.repository

import com.example.caloriecounter.model.recipelist.Recipe
import com.example.caloriecounter.model.recipenutrients.Nutrition
import com.example.caloriecounter.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getRecipeByName(name: String): Flow<Resource<List<Recipe>>>
    fun getRecipeByNutrition(id: Int): Flow<Resource<Nutrition>>
}