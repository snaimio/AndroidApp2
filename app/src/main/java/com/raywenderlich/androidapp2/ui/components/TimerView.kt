package com.raywenderlich.androidapp2.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.raywenderlich.androidapp2.ui.theme.CoffeeBrown
import kotlinx.coroutines.delay

/**
 * 3-second animated countdown timer used during order preparation.
 *
 * @param onTimerDone Callback invoked when countdown reaches 0.
 */
@Composable
fun TimerView(
    onTimerDone: () -> Unit
) {
    var timeRemaining by remember { mutableIntStateOf(3) }

    LaunchedEffect(Unit) {
        while (timeRemaining > 0) {
            delay(1000L)
            timeRemaining -= 1
        }
        onTimerDone()
    }

    Text(
        text = "$timeRemaining",
        fontSize = 48.sp,
        fontWeight = FontWeight.Bold,
        color = CoffeeBrown
    )
}
