package com.example.caloriecounter.presentation.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.caloriecounter.presentation.MainViewModel

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(viewModel: MainViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var searchValue by remember {
        mutableStateOf("")
    }
    OutlinedTextField(modifier = Modifier
        .padding(15.dp)
        .fillMaxWidth(),
        value = searchValue,
        singleLine = true,
        label = { Text(text = "Enter Food to Search") },
        onValueChange = { searchValue = it },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Go),
        keyboardActions = KeyboardActions(
            onDone = {
                keyboardController?.hide()
            },
            onGo = {
                keyboardController?.hide()
                viewModel.getRecipes(searchValue)
                searchValue = ""
            }
        ))
}