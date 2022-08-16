package com.example.caloriecounter.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caloriecounter.presentation.recipe.RecipeUIState
import com.example.caloriecounter.presentation.search.FoodUIState
import com.example.caloriecounter.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val foodRepository: FoodRepository
) : ViewModel() {

    var foodUIState by mutableStateOf(FoodUIState())
    var recipeUIState by mutableStateOf(RecipeUIState())

    fun getRecipes(name: String) {
        if (name.isNotEmpty()) {
            foodUIState = FoodUIState(isLoading = true)
            viewModelScope.launch {
                foodRepository.getRecipeByName(name)
                    .collect {
                        if (it.isNotEmpty()) {
                            foodUIState = FoodUIState(recipes = it, isLoading = false)
                        }
                    }
            }
        } else {
            foodUIState = FoodUIState(
                recipes = emptyList(),
                isLoading = false,
                message = "Search Value cannot be empty"
            )
        }
    }

    fun getRecipeById(id: Int) {
        viewModelScope.launch {
            foodRepository.getRecipeById(id)
                .collect {
                    recipeUIState = RecipeUIState(recipe = it, isLoading = false)
                }
        }
    }

    fun getIngredientsById(id: Int) {
        if (recipeUIState.ingredients.isEmpty()) {
            viewModelScope.launch {
                foodRepository.getIngredientsById(id)
                    .collect { ingredients ->
                        recipeUIState.recipe?.let {
                            recipeUIState = RecipeUIState(
                                recipe = it,
                                ingredients = ingredients.ingredients,
                                isLoading = false
                            )
                        }
                    }
            }
        }
    }
}