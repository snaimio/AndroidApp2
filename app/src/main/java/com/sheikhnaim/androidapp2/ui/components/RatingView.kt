package com.sheikhnaim.androidapp2.ui.components

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
import com.sheikhnaim.androidapp2.ui.theme.CoffeeBrown

/**
 * ============================================================================
 * COMPOSABLE: RatingView
 * ============================================================================
 * Equivalent to SwiftUI's `RatingView`.
 *
 * Renders 5 interactive coffee cup icons:
 *  - Cups <= current rating are filled in CoffeeBrown.
 *  - Cups > current rating are outlined in Gray.
 *  - Tapping cup #N updates the rating state to N via State Hoisting.
 *
 * @param rating Current rating value (0 if unrated, 1 to 5 when selected).
 * @param onRatingChange Lambda triggered when a coffee cup is tapped.
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
                contentDescription = "Rate $i coffee cups",
                tint = if (i <= rating) CoffeeBrown else Color.Gray,
                modifier = Modifier.clickable { onRatingChange(i) }
            )
        }
    }
}
