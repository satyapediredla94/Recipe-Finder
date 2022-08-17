package com.example.caloriecounter.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.caloriecounter.presentation.recipe.RecipeUIState
import com.example.caloriecounter.presentation.search.FoodUIState
import com.example.caloriecounter.repository.FoodRepository
import com.example.caloriecounter.utils.Resource
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
                        when (it) {
                            is Resource.Success -> {
                                if (it.dataList.isNotEmpty()) {
                                    foodUIState = FoodUIState(recipes = it.dataList)
                                }
                            }
                            is Resource.Error -> {
                                foodUIState = FoodUIState(message = it.errorMessage)
                            }
                            is Resource.Loading -> {
                                foodUIState = foodUIState.copy(isLoading = it.isLoading)
                            }
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
                    recipeUIState = when (it) {
                        is Resource.Success -> {
                            RecipeUIState(recipe = it.dataList)
                        }
                        is Resource.Error -> {
                            RecipeUIState(message = it.errorMessage)
                        }
                        is Resource.Loading -> {
                            recipeUIState.copy(isLoading = it.isLoading)
                        }
                    }
                }
        }
    }

    fun getIngredientsById(id: Int) {
        if (recipeUIState.ingredients.isEmpty()) {
            viewModelScope.launch {
                foodRepository.getIngredientsById(id)
                    .collect {
                        recipeUIState = when (it) {
                            is Resource.Success -> {
                                recipeUIState.copy(ingredients = it.dataList)
                            }
                            is Resource.Error -> {
                                RecipeUIState(message = it.errorMessage)
                            }
                            is Resource.Loading -> {
                                recipeUIState.copy(isLoading = it.isLoading)
                            }
                        }
                    }
            }
        }
    }
}