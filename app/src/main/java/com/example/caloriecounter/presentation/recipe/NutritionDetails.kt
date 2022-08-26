package com.example.caloriecounter.presentation.recipe

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caloriecounter.model.recipenutrients.NutrientX

@Composable
fun NutritionDetails(nutritionDetails: List<NutrientX>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp)
    ) {
        items(nutritionDetails) { nutrient ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(Modifier.padding(10.dp)) {
                    Text(
                        text = nutrient.name.capitalize(Locale.current),
                        fontSize = 18.sp
                    )
                    VerticalSpacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "${nutrient.amount} ${nutrient.unit}",
                        fontSize = 16.sp
                    )
                    VerticalSpacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = "Daily amount needed: ${nutrient.percentOfDailyNeeds} ${nutrient.unit}",
                        fontSize = 16.sp
                    )
                }

            }
            VerticalSpacer()
        }
    }
}