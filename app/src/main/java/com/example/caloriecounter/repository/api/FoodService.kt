package com.example.caloriecounter.repository.api

import com.example.caloriecounter.model.recipelist.RecipeResponse
import com.example.caloriecounter.model.recipenutrients.Nutrition
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
    suspend fun getRecipeByNutrients(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String,
        @Query("includeNutrition") includeNutrients: Boolean = true
    ): Nutrition

    @GET("/recipes/{id}/similar")
    suspend fun getSimilarRecipe(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    )

}