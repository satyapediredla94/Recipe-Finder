package com.example.caloriecounter.repository.local

import com.example.caloriecounter.model.ingredients.IngredientsResponse
import com.example.caloriecounter.model.recipe.RecipeData
import com.example.caloriecounter.model.recipelist.Recipe
import com.example.caloriecounter.repository.FoodRepository
import kotlinx.coroutines.flow.Flow

class FoodRepositoryImpl : FoodRepository {
    override fun getRecipeByName(name: String): Flow<List<Recipe>> {
        TODO("Not yet implemented")
    }

    override fun getRecipeById(id: Int): Flow<RecipeData> {
        TODO("Not yet implemented")
    }

    override fun getIngredientsById(id: Int): Flow<IngredientsResponse> {
        TODO("Not yet implemented")
    }
}