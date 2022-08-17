package com.example.caloriecounter.repository

import com.example.caloriecounter.model.db.LocalIngredient
import com.example.caloriecounter.model.ingredients.IngredientsResponse
import com.example.caloriecounter.model.recipe.RecipeData
import com.example.caloriecounter.model.recipelist.Recipe
import com.example.caloriecounter.utils.Resource
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getRecipeByName(name: String): Flow<Resource<List<Recipe>>>
    fun getRecipeById(id: Int): Flow<Resource<RecipeData>>
    fun getIngredientsById(id: Int): Flow<Resource<List<LocalIngredient>>>
}