package com.example.caloriecounter.presentation.screens

import com.example.caloriecounter.R

sealed class Screens(val route: String, val title: String) {
    object Search: Screens(UIConstants.SEARCH, UIConstants.SEARCH)
    object Recipe: Screens(UIConstants.RECIPE, UIConstants.RECIPE)
}
