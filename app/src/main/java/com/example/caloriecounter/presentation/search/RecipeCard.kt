package com.example.caloriecounter.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.compose.AsyncImage
import com.example.caloriecounter.presentation.screens.UIConstants
import com.example.caloriecounter.utils.PagingUIState

@Composable
fun RecipeCard(
    state: PagingUIState,
    navController: NavHostController
) {

    val recipes = state.recipeData!!.collectAsLazyPagingItems()

    LazyColumn(
        modifier = Modifier.padding(10.dp),
        content = {
            items(recipes) { recipe ->
                val recipeId = recipe!!.id.toString().trim()
                Card(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("${UIConstants.RECIPE}/$recipeId") {
                                popUpTo("home")
                            }
                        }
                ) {
                    Column {
                        Row {
                            AsyncImage(
                                model = recipe.image, contentDescription = recipe.title,
                                modifier = Modifier.fillMaxHeight(),
                                contentScale = ContentScale.FillHeight
                            )
                            Spacer(Modifier.width(10.dp))
                            Text(
                                text = recipe.title, fontSize = 16.sp,
                                modifier = Modifier.padding(top = 10.dp)
                            )
                        }
                    }
                }
            }

            when (recipes.loadState.append) {
                LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                is LoadState.Error -> {
                    item {
                        Text(
                            text = "Something happened from backend",
                            fontSize = 20.sp,
                            modifier = Modifier.padding(15.dp)
                        )
                    }
                }
                else -> {}
            }

            when (recipes.loadState.refresh) {
                is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                is LoadState.Error -> {
                    val errorMessage =
                        ((recipes.loadState.refresh as LoadState.Error).error as Exception).localizedMessage?.toString()
                            ?: ""
                    item {
                        Text(text = errorMessage)
                    }
                }
                else -> {}
            }
        },
        reverseLayout = false
    )
}