package com.example.caloriecounter.presentation.recipe

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.caloriecounter.model.recipenutrients.Nutrition
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun RenderPagerState(
    recipeNutrition: Nutrition
) {
    val tabData = listOf("Recipe", "Ingredients", "Nutrients")
    val pagerState = rememberPagerState()
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column(
        Modifier
            .fillMaxWidth()
    ) {
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
            }
        }
    }


}