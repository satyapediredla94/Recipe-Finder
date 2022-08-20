package com.example.caloriecounter.presentation.recipe

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Instructions(instruction: String) {
    val instructions = instruction.split("\n")
    if (instructions.size > 1) {
        for (step in instructions) {
            RenderInstruction(instruction = step)
        }
    } else {
        val steps = instruction.trim().split("<ol>")
        steps.forEach { recipeStep ->
            recipeStep.trim().split("<li>").forEach {
                RenderInstruction(instruction = it)
            }
        }
    }
}

@Composable
fun RenderInstruction(instruction: String) {
    if (instruction.trim().isNotBlank()) {
        Card {
            Text(
                text = instruction
                    .replace("</li>", "")
                    .replace("</ol>", ""),
                Modifier.padding(10.dp)
            )
        }
        VerticalSpacer(modifier = Modifier.height(2.dp))
    }
}