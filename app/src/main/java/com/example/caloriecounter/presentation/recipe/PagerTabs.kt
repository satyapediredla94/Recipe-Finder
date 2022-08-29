package com.example.caloriecounter.presentation.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.caloriecounter.model.SimilarRecipe
import com.example.caloriecounter.model.recipenutrients.Nutrition
import com.example.caloriecounter.presentation.VerticalSpacer
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RenderRecipePaging(
    recipeUIState: RecipeUIState,
    recipeNutrition: Nutrition,
    navController: NavHostController,
    modifier: Modifier
) {
    val tabData = listOf("Recipe", "Ingredients", "Nutrients", "Similar")
    val pagerState = rememberPagerState()

    Column(
        modifier = modifier
    ) {
        TabLayout(tabData,pagerState)
        HorizontalPager(
            count = tabData.size,
            state = pagerState,
        ) {
            when (pagerState.currentPage) {
                0 ->
                    recipeNutrition.instructions?.let {
                        Instructions(instruction = it)
                    }
                1 -> {
                    recipeNutrition.extendedIngredients?.let {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            if (it.isNotEmpty()) {
                                Ingredients(ingredients = it)
                            }
                        }
                    }
                }
                2 -> {
                    recipeNutrition.nutrition?.let { nutritionX ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                        ) {
                            if (nutritionX.nutrients.isNotEmpty()) {
                                NutritionDetails(nutritionX.nutrients)
                            }
                        }
                    }
                }
                3 -> {
                    recipeUIState.similarRecipe?.let {
                        if (it.isNotEmpty()) {
                            VerticalSpacer()
                            SimilarRecipeCards(
                                it, navController,
                                modifier = Modifier.fillMaxSize()
                            )
                        }
                    }
                }
            }
        }
    }


}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabLayout(tabData: List<String>, pagerState: PagerState) {
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()
    TabRow(
        // Our selected tab is our current page
        selectedTabIndex = tabIndex,
        // Override the indicator, using the provided pagerTabIndicatorOffset modifier
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ) {
        // Add tabs for all of our pages
        tabData.forEachIndexed { index, it ->
            Tab(
                text = { Text(it) },
                selected = pagerState.currentPage == index,
                onClick = { coroutineScope.launch { pagerState.scrollToPage(index) } },
            )
        }
    }
}
