package com.raywenderlich.androidapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.raywenderlich.androidapp2.ui.screens.MainContainerScreen
import com.raywenderlich.androidapp2.ui.theme.AndroidApp2Theme

/**
 * Main Android Entry Point Activity.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidApp2Theme {
                MainContainerScreen()
            }
        }
    }
}
