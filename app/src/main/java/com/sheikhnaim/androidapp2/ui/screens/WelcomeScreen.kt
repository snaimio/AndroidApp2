package com.sheikhnaim.androidapp2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sheikhnaim.androidapp2.ui.components.HeaderView
import com.sheikhnaim.androidapp2.ui.theme.CoffeeBrown

/**
 * ============================================================================
 * SCREEN: WelcomeScreen
 * ============================================================================
 * Equivalent to SwiftUI's `WelcomeView` (Tab 0).
 *
 * Features:
 *  - Header with title "Welcome" (no circle indicators).
 *  - Large coffee cup branding icon.
 *  - "Team Coffee Run" title & tagline.
 *  - "Get Started" button to navigate to Alex's order page (Page 1).
 *  - "Order History" button to open the history modal bottom sheet.
 *
 * @param onGetStartedClick Callback to animate pager to page 1.
 * @param onHistoryClick Callback to open the order history bottom sheet.
 */
@Composable
fun WelcomeScreen(
    onGetStartedClick: () -> Unit,
    onHistoryClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header without page numbers
        HeaderView(
            titleText = "Welcome",
            currentPage = 0,
            showPageNumbers = false,
            onPageSelected = {}
        )

        Spacer(modifier = Modifier.weight(1f))

        // Large Coffee Cup Icon Branding
        Icon(
            imageVector = Icons.Filled.Coffee,
            contentDescription = "Coffee Logo",
            tint = CoffeeBrown,
            modifier = Modifier.size(90.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // App Title & Tagline
        Text(
            text = "Team Coffee Run",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = CoffeeBrown
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = "Order together, save time",
            fontSize = 18.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1.5f))

        // "Get Started" Button -> Takes user to Alex's order screen (Page 1)
        Button(
            onClick = onGetStartedClick,
            shape = RoundedCornerShape(25.dp),
            colors = ButtonDefaults.buttonColors(containerColor = CoffeeBrown),
            modifier = Modifier.height(50.dp)
        ) {
            Text(text = "Get Started", fontSize = 18.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Forward"
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // "Order History" Button -> Opens the bottom sheet history dialog
        TextButton(onClick = onHistoryClick) {
            Icon(
                imageVector = Icons.Filled.Schedule,
                contentDescription = "History Icon",
                tint = CoffeeBrown
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "Order History",
                color = CoffeeBrown,
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}
