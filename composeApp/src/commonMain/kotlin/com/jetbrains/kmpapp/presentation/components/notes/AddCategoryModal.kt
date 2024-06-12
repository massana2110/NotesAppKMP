package com.jetbrains.kmpapp.presentation.components.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.jetbrains.kmpapp.presentation.IndianRed
import com.jetbrains.kmpapp.presentation.MediumSeaGreen

@Composable
fun AddCategoryModal(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onSaveCategory: (String, Color) -> Unit
) {

    var categoryName by remember { mutableStateOf("") }
    var colorSelected by remember { mutableStateOf(listColors.first()) }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(8.dp),
        ) {
            Text(
                text = "Add category",
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 24.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )

            TextField(
                value = categoryName,
                onValueChange = { categoryName = it },
                singleLine = true,
                maxLines = 1,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp, top = 16.dp),
                label = { Text("Category name") },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                )
            )

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Category color",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            ColorsRowList(
                modifier = Modifier.padding(horizontal = 8.dp),
                colorSelected = colorSelected,
                onColorSelected = { colorSelected = it }
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Cancel", color = IndianRed)
                }
                TextButton(onClick = { onSaveCategory(categoryName, colorSelected.first) }) {
                    Text(text = "Save", color = MediumSeaGreen)
                }
            }
        }
    }

}