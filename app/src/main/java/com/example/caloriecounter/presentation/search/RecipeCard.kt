package com.example.caloriecounter.presentation.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.caloriecounter.presentation.screens.UIConstants

@Composable
fun RecipeCard(
    state: FoodUIState,
    navController: NavHostController
) {
    LazyColumn(
        modifier = Modifier.padding(10.dp),
        content = {
            items(state.recipes) { recipe ->
                val recipeId = recipe.id.toString().trim()
                Card(
                    modifier = Modifier
                        .padding(15.dp)
                        .fillMaxWidth()
                        .clickable {
                            navController.navigate("${UIConstants.RECIPE}/$recipeId")
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
        })
}