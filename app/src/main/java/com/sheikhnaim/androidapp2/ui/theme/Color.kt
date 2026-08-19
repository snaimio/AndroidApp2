package com.sheikhnaim.androidapp2.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * ============================================================================
 * THEME COLORS
 * ============================================================================
 * Translates custom colors from the iOS SwiftUI app.
 */

// Warm rich coffee brown: Color(red: 139/255, green: 69/255, blue: 19/255) -> #8B4513
val CoffeeBrown = Color(0xFF8B4513)

// Semi-transparent coffee tint (~15% opacity) for unselected buttons & pill backgrounds
val CoffeeBrownLight = Color(0x268B4513)

// Distinct blue accent used for Milk customization pills
val MilkBlue = Color(0xFF2196F3)
val MilkBlueLight = Color(0x262196F3)

// Status feedback colors
val OrderGreen = Color(0xFF4CAF50)    // Success / Order Ready checkmarks & Next button
val RatingOrange = Color(0xFFFF9800)  // Prompt button to rate drink
val SparkleYellow = Color(0xFFFFD700) // Celebratory sparkles on Success screen
