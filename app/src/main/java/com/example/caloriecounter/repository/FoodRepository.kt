package com.example.caloriecounter.repository

import com.example.caloriecounter.model.ingredients.IngredientsResponse
import com.example.caloriecounter.model.recipe.RecipeData
import com.example.caloriecounter.model.recipelist.Recipe
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getRecipeByName(name: String): Flow<List<Recipe>>
    fun getRecipeById(id: Int): Flow<RecipeData>
    fun getIngredientsById(id: Int): Flow<IngredientsResponse>
}