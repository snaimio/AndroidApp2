package com.raywenderlich.androidapp2.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.raywenderlich.androidapp2.ui.theme.CoffeeBrown

/**
 * 5-cup interactive rating bar matching iOS RatingView.
 *
 * @param rating Current rating (1 to 5, or 0 if unrated).
 * @param onRatingChange Callback invoked when a cup icon is tapped.
 */
@Composable
fun RatingView(
    rating: Int,
    onRatingChange: (Int) -> Unit
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rating) Icons.Filled.Coffee else Icons.Outlined.Coffee,
                contentDescription = "Rate $i stars",
                tint = if (i <= rating) CoffeeBrown else Color.Gray,
                modifier = Modifier.clickable { onRatingChange(i) }
            )
        }
    }
}
