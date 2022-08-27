package com.example.caloriecounter.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.caloriecounter.MyTopBar
import com.example.caloriecounter.presentation.recipe.RecipeDetail
import com.example.caloriecounter.presentation.screens.Screens
import com.example.caloriecounter.presentation.screens.UIConstants
import com.example.caloriecounter.presentation.search.RecipeCard
import com.example.caloriecounter.presentation.search.SearchBar


@Composable
fun Content() {
    val viewModel: MainViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()
    val routes = listOf(
        Screens.Search.route
    )
    val navController = rememberNavController()
    val showTopAndBottomBar =
        navController.currentBackStackEntryAsState().value?.destination?.route in routes
    Scaffold(
        topBar = { if (showTopAndBottomBar) MyTopBar() },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                val foodUIState = viewModel.foodUIState
                val pagingUIState = viewModel.pagingUIState
                NavHost(navController = navController, startDestination = Screens.Search.route) {
                    composable(Screens.Search.route) {
                        viewModel.resetRecipeUIState()
                        Column {
                            SearchBar(viewModel = viewModel)
                            if (foodUIState.isLoading) {
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                            }
                            /*if (foodUIState.recipes.isNotEmpty()) {
                                RecipeCard(
                                    state = foodUIState,
                                    navController = navController
                                )
                            }*/
                            pagingUIState.recipeData?.let {
                                RecipeCard(state = pagingUIState, navController = navController)
                            }
                        }
                    }
                    composable(
                        "${UIConstants.RECIPE}/{recipeId}",
                        arguments = listOf(navArgument("recipeId") {
                            type = NavType.StringType
                            defaultValue = ""
                        })
                    ) { navBackStackEntry ->
                        val recipeId = navBackStackEntry.arguments?.get("recipeId") as String
                        RecipeDetail(
                            recipeId = recipeId.toInt(),
                            viewModel = viewModel,
                            navController = navController
                        )
                    }
                }

            }
        },
        scaffoldState = scaffoldState
    )
}