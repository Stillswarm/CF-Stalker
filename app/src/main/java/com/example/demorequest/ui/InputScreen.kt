package com.example.demorequest.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InputScreen(
    onInfoRequest: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val textFieldValues = remember {
        mutableStateListOf("")
    }

    var noOfEntries by remember {
        mutableIntStateOf(1)
    }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        for (i in 0..<noOfEntries) {
            item {
                TextField(
                    value = textFieldValues[i],
                    onValueChange = { textFieldValues[i] = it },
                    label = { Text(text = "Enter a CF handle") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
                )

                Spacer(Modifier.height(20.dp))
            }
        }

        item {
            OutlinedButton(onClick = {
                ++noOfEntries
                textFieldValues.add("")
                Log.d("fck", "${textFieldValues.size}, ${noOfEntries}")
            }) {
                Text("Add New Entry")
            }

            Spacer(Modifier.height(20.dp))

            Button(
                onClick = {
                    val finalStr = StringBuilder()
                    textFieldValues.forEach {handle ->
                        finalStr.append("$handle;")
                    }
                    onInfoRequest(finalStr.toString())
                }
            ) {
                Text("Get User Info")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun InputScreenPreview() {
    InputScreen(onInfoRequest = {})
}