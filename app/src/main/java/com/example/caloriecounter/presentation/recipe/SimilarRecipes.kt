package com.example.caloriecounter.presentation.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Timer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.caloriecounter.model.SimilarRecipe
import com.example.caloriecounter.presentation.VerticalSpacer
import com.example.caloriecounter.presentation.screens.UIConstants
import com.example.caloriecounter.utils.ApiUtils.BASE_RECIPE_IMAGE_URL

@Composable
fun SimilarRecipeCards(
    similar: List<SimilarRecipe>,
    navController: NavController,
    modifier: Modifier
) {
    Box(modifier = modifier) {
        LazyColumn {
            items(similar) { recipe ->
                Card(
                    modifier = Modifier
                        .clip(RoundedCornerShape(15.dp))
                        .clickable {
                            navController.navigate("${UIConstants.RECIPE}/${recipe.id}") {
                                popUpTo("home")
                            }
                        }
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(bottom = 10.dp)
                    ) {
                        AsyncImage(
                            model = "$BASE_RECIPE_IMAGE_URL${recipe.id}-312x231.jpg",
                            contentDescription = "",
                            contentScale = ContentScale.FillWidth
                        )
                        Spacer(Modifier.height(10.dp))
                        Column(Modifier.fillMaxHeight(0.25f)) {
                            Text(
                                text = recipe.title,
                                style = MaterialTheme.typography.body1,
                                modifier = Modifier.padding(start = 8.dp),
                                fontWeight = FontWeight.Bold,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            VerticalSpacer(modifier = Modifier.height(5.dp))
                            Row {
                                Image(
                                    imageVector = Icons.Filled.Timer, contentDescription = "",
                                    colorFilter = ColorFilter.tint(MaterialTheme.colors.secondary)
                                )
                                VerticalSpacer(modifier = Modifier.width(4.dp))
                                Text(text = "${recipe.readyInMinutes} min")
                            }
                        }
                    }
                }
            }
        }
    }
}