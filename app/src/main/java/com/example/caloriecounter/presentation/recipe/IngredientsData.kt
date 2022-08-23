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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.caloriecounter.model.db.LocalIngredient
import com.example.caloriecounter.utils.ApiUtils.BASE_IMAGE_URL

@Composable
fun Ingredients(ingredients: List<LocalIngredient>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        items(ingredients) { ingredient ->
            Card(
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
            ) {
                Row(Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = BASE_IMAGE_URL + ingredient.image, contentDescription = "",
                        modifier = Modifier
                            .height(100.dp)
                            .width(100.dp),
                        contentScale = ContentScale.FillWidth
                    )
                    HorizontalSpacer(modifier = Modifier.height(2.dp))
                    Column(Modifier.padding(10.dp)) {
                        Text(
                            text = ingredient.name,
                            fontSize = 16.sp
                        )
                        VerticalSpacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "${ingredient.amount.us.value} ${ingredient.amount.us.unit}",
                            fontSize = 16.sp
                        )
                    }
                }
            }
            VerticalSpacer()
        }
    }

}