package com.sheikhnaim.androidapp2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sheikhnaim.androidapp2.ui.theme.CoffeeBrown
import com.sheikhnaim.androidapp2.ui.theme.SparkleYellow

/**
 * ============================================================================
 * SCREEN: SuccessScreen
 * ============================================================================
 * Equivalent to SwiftUI's `SuccessView`.
 *
 * Appears after Casey (the final team member) finishes ordering.
 * Features:
 *  - Celebratory coffee cup surrounded by 3 offset yellow sparkles.
 *  - "All Orders Complete!" headline.
 *  - Motivational team success message.
 *  - "Back to Welcome" button returning the app back to Tab 0.
 *
 * @param onBackToWelcome Callback returning to the Welcome screen.
 */
@Composable
fun SuccessScreen(
    onBackToWelcome: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Coffee cup with celebratory sparkles around it matching iOS SuccessView
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(140.dp)
            ) {
                // Main Coffee Cup
                Icon(
                    imageVector = Icons.Filled.Coffee,
                    contentDescription = "Success Coffee Cup",
                    tint = CoffeeBrown,
                    modifier = Modifier.size(80.dp)
                )

                // Sparkle 1 (Top Left)
                Icon(
                    imageVector = Icons.Filled.AutoAwesome,
                    contentDescription = null,
                    tint = SparkleYellow,
                    modifier = Modifier
                        .size(24.dp)
                        .offset(x = (-35).dp, y = (-35).dp)
                )

                // Sparkle 2 (Top Right)
                Icon(
                    imageVector = Icons.Filled.AutoAwesome,
                    contentDescription = null,
                    tint = SparkleYellow,
                    modifier = Modifier
                        .size(18.dp)
                        .offset(x = 35.dp, y = (-25).dp)
                )

                // Sparkle 3 (Bottom Right)
                Icon(
                    imageVector = Icons.Filled.AutoAwesome,
                    contentDescription = null,
                    tint = SparkleYellow,
                    modifier = Modifier
                        .size(16.dp)
                        .offset(x = 25.dp, y = 35.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "All Orders Complete!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = CoffeeBrown,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Great job team!\nCoffee run successful. ☕️",
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = onBackToWelcome,
                colors = ButtonDefaults.buttonColors(containerColor = CoffeeBrown),
                shape = RoundedCornerShape(25.dp),
                modifier = Modifier
                    .width(220.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Back to Welcome",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
