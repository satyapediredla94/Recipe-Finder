package com.example.caloriecounter.presentation.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
                Column(Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = ingredient.image, contentDescription = "",
                        modifier = Modifier
                            .fillMaxHeight(0.7f),
                        contentScale = ContentScale.FillWidth
                    )
                    VerticalSpacer(modifier = Modifier.height(2.dp))
                    Text(text = ingredient.name)
                    VerticalSpacer(modifier = Modifier.height(2.dp))
                    Text(text = "${ingredient.amount.us.value} ${ingredient.amount.us.unit}")
                }
            }
            VerticalSpacer()
        }
    }

}