package com.sheikhnaim.androidapp2.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sheikhnaim.androidapp2.ui.theme.CoffeeBrown
import com.sheikhnaim.androidapp2.ui.theme.CoffeeBrownLight

/**
 * Top navigation header displaying the screen title and clickable page circles.
 *
 * @param titleText Title text to show.
 * @param currentPage Current page index in the horizontal pager (0 = Welcome, 1..4 = Members).
 * @param showPageNumbers If true, renders numbered circle buttons for each team member.
 * @param teamMembers List of team member names.
 * @param onPageSelected Callback triggered when a user taps a member circle.
 */
@Composable
fun HeaderView(
    titleText: String,
    currentPage: Int,
    showPageNumbers: Boolean = true,
    teamMembers: List<String> = listOf("Alex", "Jordan", "Taylor", "Casey"),
    onPageSelected: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main title in Coffee Brown
        Text(
            text = if (showPageNumbers) titleText else "Welcome",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            color = CoffeeBrown
        )

        // Numbered indicator circles to jump directly to any team member
        if (showPageNumbers) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                teamMembers.forEachIndexed { index, name ->
                    val pageIndex = index + 1 // +1 because 0 is Welcome
                    val isSelected = currentPage == pageIndex

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.clickable { onPageSelected(pageIndex) }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clip(CircleShape)
                                .background(if (isSelected) CoffeeBrownLight else Color.Transparent),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${index + 1}",
                                fontSize = 18.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) CoffeeBrown else Color.Gray
                            )
                        }
                        Text(
                            text = name,
                            fontSize = 11.sp,
                            color = if (isSelected) CoffeeBrown else Color.Gray
                        )
                    }
                }
            }
        }
    }
}
