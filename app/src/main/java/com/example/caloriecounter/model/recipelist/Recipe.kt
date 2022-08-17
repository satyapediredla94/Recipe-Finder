package com.example.caloriecounter.model.recipelist

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Recipe(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val image: String,
    val imageType: String,
    val title: String
)