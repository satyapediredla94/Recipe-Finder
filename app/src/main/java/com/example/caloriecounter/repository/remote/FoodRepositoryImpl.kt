package com.example.caloriecounter.repository.remote

import com.example.caloriecounter.model.recipelist.Recipe
import com.example.caloriecounter.model.recipenutrients.Nutrition
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

    override fun getRecipeByNutrition(id: Int): Flow<Resource<Nutrition>> = flow {
        emit(Resource.Loading(true))
        Timber.e("Getting Recipe using $id")
        try {
            val recipeData = foodApiService.getRecipeByNutrients(id, apiKey)
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
            val recipe = withContext(dispatcher) { foodRepo.getRecipeByNutrition(id) }
            emit(Resource.Success(dataList = recipe))
            emit(Resource.Loading(false))
        }
    }
}