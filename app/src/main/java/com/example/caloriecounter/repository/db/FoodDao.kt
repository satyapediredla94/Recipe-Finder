package com.example.caloriecounter.repository.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.caloriecounter.model.recipelist.Recipe
import com.example.caloriecounter.model.recipenutrients.Nutrition

@Dao
interface FoodDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipeNutrition(recipe: Nutrition)

    @Query("SELECT * FROM Recipe WHERE title LIKE '%' || :searchWord || '%'")
    fun getRecipeResponse(searchWord: String): List<Recipe>


    @Query("SELECT * FROM Nutrition WHERE id=:recipeId")
    fun getRecipeDataByNutrition(recipeId: Int): Nutrition

}