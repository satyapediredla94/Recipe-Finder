package com.example.caloriecounter.utils

import androidx.paging.PagingData
import com.example.caloriecounter.model.recipelist.Recipe
import kotlinx.coroutines.flow.Flow


data class PagingUIState(
    var recipeData: Flow<PagingData<Recipe>>? = null
)