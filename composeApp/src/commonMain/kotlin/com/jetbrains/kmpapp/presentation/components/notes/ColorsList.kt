package com.jetbrains.kmpapp.presentation.components.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jetbrains.kmpapp.presentation.DeepSkyBlue
import com.jetbrains.kmpapp.presentation.DimGray
import com.jetbrains.kmpapp.presentation.GoldenYellow
import com.jetbrains.kmpapp.presentation.IndianRed
import com.jetbrains.kmpapp.presentation.LightPink
import com.jetbrains.kmpapp.presentation.LightYellow
import com.jetbrains.kmpapp.presentation.MediumSeaGreen
import com.jetbrains.kmpapp.presentation.MediumTurquoise
import com.jetbrains.kmpapp.presentation.Purple

val listColors = listOf(
    Pair(GoldenYellow, Color.Black),
    Pair(IndianRed, Color.White),
    Pair(DeepSkyBlue, Color.Black),
    Pair(Purple, Color.White),
    Pair(MediumSeaGreen, Color.Black),
    Pair(DimGray, Color.Black),
    Pair(MediumTurquoise, Color.White),
    Pair(LightPink, Color.Black),
    Pair(LightYellow, Color.Black),
)

@Composable
fun ColorsRowList(
    modifier: Modifier = Modifier,
    colorSelected: Pair<Color, Color>,
    onColorSelected: (Pair<Color, Color>) -> Unit
) {
    LazyRow(modifier = modifier, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        items(listColors) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(it.first)
                    .clickable { onColorSelected(it) },
                contentAlignment = Alignment.Center
            ) {
                if (colorSelected == it) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = it.toString()
                    )
                }
            }
        }
    }
}