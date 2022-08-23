package com.example.caloriecounter.presentation.recipe

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.caloriecounter.model.db.LocalIngredient

@Composable
fun Ingredients(ingredients: List<LocalIngredient>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        items(ingredients) { ingredient ->
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            ) {
                Row(Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = ingredient.image, contentDescription = "",
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp),
                        contentScale = ContentScale.FillWidth
                    )
                    HorizontalSpacer(modifier = Modifier.height(2.dp))
                    Column {
                        Text(text = ingredient.name)
                        VerticalSpacer(modifier = Modifier.height(2.dp))
                        Text(text = "${ingredient.amount.us.value} ${ingredient.amount.us.unit}")
                    }
                }
            }
            VerticalSpacer()
        }
    }

}