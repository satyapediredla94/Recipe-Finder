package com.example.caloriecounter.repository.local

import com.example.caloriecounter.model.db.LocalIngredient
import com.example.caloriecounter.model.ingredients.Ingredient
import com.example.caloriecounter.model.recipe.recipedata.RecipeData
import com.example.caloriecounter.model.recipelist.Recipe
import com.example.caloriecounter.repository.db.FoodDao

class FoodRepositoryImpl(
    private val foodDao: FoodDao
) {
    fun insertRecipe(recipeList: List<Recipe>) {
        recipeList.forEach { recipe ->
            foodDao.insertRecipe(recipe)
        }
    }

    fun insertIngredientsList(id: Int, ingredients: List<Ingredient>) {
        ingredients.forEach {
            val ingredient = LocalIngredient(
                recipeId = id,
                name = it.name,
                image = it.image,
                amount = it.amount
            )
            foodDao.insertIngredientsList(ingredient)
        }
    }

    fun insertRecipeData(recipe: RecipeData) = foodDao.insertRecipeData(recipe)

    fun getRecipeByName(name: String): List<Recipe> = foodDao.getRecipeResponse(name)

    fun getRecipeById(id: Int): RecipeData = foodDao.getRecipeData(id)

    fun getIngredientsById(id: Int): List<LocalIngredient> = foodDao.getIngredient(id)
}