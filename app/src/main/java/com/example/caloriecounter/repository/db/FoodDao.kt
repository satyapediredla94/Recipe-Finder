package com.example.caloriecounter.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.caloriecounter.model.db.LocalIngredient
import com.example.caloriecounter.model.recipe.RecipeData
import com.example.caloriecounter.model.recipelist.Recipe

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIngredientsList(ingredient: LocalIngredient)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipeData(recipe: RecipeData)

    @Query("SELECT * FROM Recipe WHERE title LIKE '%' || :searchWord || '%'")
    fun getRecipeResponse(searchWord: String): List<Recipe>

    @Query("SELECT * FROM LocalIngredient WHERE recipeId=:recipeId")
    fun getIngredient(recipeId: Int): List<LocalIngredient>

    @Query("SELECT * FROM RecipeData WHERE id=:recipeId")
    fun getRecipeData(recipeId: Int): RecipeData

}