package com.example.caloriecounter.repository.remote

import com.example.caloriecounter.model.db.LocalIngredient
import com.example.caloriecounter.model.recipe.RecipeData
import com.example.caloriecounter.model.recipelist.Recipe
import com.example.caloriecounter.repository.FoodRepository
import com.example.caloriecounter.repository.api.FoodService
import com.example.caloriecounter.repository.local.FoodRepositoryImpl
import com.example.caloriecounter.utils.ApiUtils
import com.example.caloriecounter.utils.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class FoodRepositoryImpl @Inject constructor(
    private val foodApiService: FoodService,
    private val foodRepo: FoodRepositoryImpl,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : FoodRepository {

    private val apiKey
        get() = ApiUtils.API_KEY

    override fun getRecipeByName(name: String): Flow<Resource<List<Recipe>>> = flow {
        emit(Resource.Loading(true))
        try {
            val recipeList = foodApiService.getRecipeByName(name, apiKey).recipe
            withContext(dispatcher) {
                foodRepo.insertRecipe(recipeList)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
        } finally {
            val recipeList = withContext(dispatcher) {
                foodRepo.getRecipeByName(name)
            }
            emit(Resource.Success(dataList = recipeList))
            emit(Resource.Loading(false))
        }
    }

    override fun getRecipeById(id: Int): Flow<Resource<RecipeData>> = flow {
        emit(Resource.Loading(true))
        Timber.e("Getting Recipe using $id")
        try {
            val recipeData = foodApiService.getRecipeById(id, apiKey)
            Timber.e("Getting Recipe from API Call")
            withContext(dispatcher) {
                Timber.e("Launching Recipe API Call")
                foodRepo.insertRecipeData(recipeData)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Timber.e("Caught Exception in Recipe API")
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
        } finally {
            Timber.e("Getting Recipe from DB")
            val recipe = withContext(dispatcher) { foodRepo.getRecipeById(id) }
            emit(Resource.Success(dataList = recipe))
            emit(Resource.Loading(false))
        }
    }

    override fun getIngredientsById(id: Int): Flow<Resource<List<LocalIngredient>>> = flow {
        emit(Resource.Loading(true))
        Timber.e("Getting Ingredients using $id")
        try {
            val ingredients = foodApiService.getIngredientsById(id, apiKey).ingredients
                Timber.e("Getting Ingredients from API Call")
            withContext(dispatcher) {
                Timber.e("Launching Ingredients API Call")
                foodRepo.insertIngredientsList(id, ingredients)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Timber.e("Received Exception Inside Ingredients")
            emit(Resource.Error(e.localizedMessage ?: "Something went wrong"))
            return@flow
        } finally {
            Timber.e("Getting Ingredients from DB")
            val ingredients = withContext(dispatcher) {
                foodRepo.getIngredientsById(id)
            }
            emit(Resource.Success(dataList = ingredients))
            emit(Resource.Loading(false))
        }
    }
}