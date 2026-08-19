package com.sheikhnaim.androidapp2.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.sheikhnaim.androidapp2.ui.theme.CoffeeBrown
import kotlinx.coroutines.delay

/**
 * ============================================================================
 * COMPOSABLE: TimerView
 * ============================================================================
 * Equivalent to SwiftUI's `TimerView` / `CountdownView` using `TimelineView`.
 *
 * In Jetpack Compose, we use `LaunchedEffect(Unit)` with coroutine `delay(1000L)`:
 *  1. Counts down from 3 to 0 seconds (1 second interval).
 *  2. Recomposes the Text UI on each second.
 *  3. Calls `onTimerDone()` when countdown hits 0 to advance the order state.
 *
 * @param onTimerDone Callback invoked when countdown finishes.
 */
@Composable
fun TimerView(
    onTimerDone: () -> Unit
) {
    var timeRemaining by remember { mutableIntStateOf(3) }

    // LaunchedEffect starts a coroutine scoped to this Composable lifecycle
    LaunchedEffect(Unit) {
        while (timeRemaining > 0) {
            delay(1000L) // Wait 1 second
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
