package com.example.caloriecounter.repository.api

import com.example.caloriecounter.model.ingredients.IngredientsResponse
import com.example.caloriecounter.model.recipe.recipedata.RecipeData
import com.example.caloriecounter.model.recipelist.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FoodService {

    @GET("/recipes/complexSearch?")
    suspend fun getRecipeByName(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String
    ): RecipeResponse

    @GET("/recipes/{id}/information")
    suspend fun getRecipeById(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): RecipeData

    @GET("/recipes/{id}/ingredientWidget.json")
    suspend fun getIngredientsById(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): IngredientsResponse

}