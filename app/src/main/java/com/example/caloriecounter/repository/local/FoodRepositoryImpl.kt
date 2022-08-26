package com.example.caloriecounter.repository.local

import com.example.caloriecounter.model.recipelist.Recipe
import com.example.caloriecounter.model.recipenutrients.Nutrition
import com.example.caloriecounter.repository.db.FoodDao

class FoodRepositoryImpl(
    private val foodDao: FoodDao
) {
    fun insertRecipe(recipeList: List<Recipe>) {
        recipeList.forEach { recipe ->
            foodDao.insertRecipe(recipe)
        }
    }

    fun insertRecipeData(recipe: Nutrition) = foodDao.insertRecipeNutrition(recipe)

    fun getRecipeByName(name: String): List<Recipe> = foodDao.getRecipeResponse(name)

    fun getRecipeByNutrition(id: Int): Nutrition = foodDao.getRecipeDataByNutrition(id)

}