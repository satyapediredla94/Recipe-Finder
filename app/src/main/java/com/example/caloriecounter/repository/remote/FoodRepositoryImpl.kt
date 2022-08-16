package com.example.caloriecounter.repository.remote

import com.example.caloriecounter.model.ingredients.IngredientsResponse
import com.example.caloriecounter.model.recipe.RecipeData
import com.example.caloriecounter.model.recipelist.Recipe
import com.example.caloriecounter.repository.FoodRepository
import com.example.caloriecounter.repository.api.FoodService
import com.example.caloriecounter.utils.ApiUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FoodRepositoryImpl(
    private val foodApiService: FoodService
) : FoodRepository {

    private val apiKey
        get() = ApiUtils.API_KEY

    override fun getRecipeByName(name: String): Flow<List<Recipe>> = flow {
        try {
            emit(foodApiService.getRecipeByName(name, apiKey).recipe)
        } catch (e: Exception) {
            e.printStackTrace()
//            e.localizedMessage?.let { error(it) }
        }
    }

    override fun getRecipeById(id: Int): Flow<RecipeData> = flow {
        try {
            emit(foodApiService.getRecipeById(id, apiKey))
        } catch (e: Exception) {
//            e.localizedMessage?.let { error(it) }
            e.printStackTrace()
        }
    }

    override fun getIngredientsById(id: Int): Flow<IngredientsResponse> = flow {
        try {
            emit(foodApiService.getIngredientsById(id, apiKey))
        } catch (e: Exception) {
//            e.localizedMessage?.let { error(it) }
            e.printStackTrace()
        }
    }
}