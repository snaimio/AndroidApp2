package com.sheikhnaim.androidapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sheikhnaim.androidapp2.ui.screens.MainContainerScreen
import com.sheikhnaim.androidapp2.ui.theme.AndroidApp2Theme

/**
 * ============================================================================
 * MAIN ACTIVITY: MainActivity
 * ============================================================================
 * Main Android entry point for the application.
 *
 * Replaces the traditional iOS `@main struct iOSApp1App: App`.
 * Uses `enableEdgeToEdge()` and Jetpack Compose's `setContent { ... }`
 * to launch `MainContainerScreen` inside the custom `AndroidApp2Theme`.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Enable edge-to-edge layout rendering
        enableEdgeToEdge()
        // Set the Jetpack Compose UI content
        setContent {
            AndroidApp2Theme {
                MainContainerScreen()
            }
        }
    }
}
