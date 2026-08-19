package com.sheikhnaim.androidapp2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.CheckCircleOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sheikhnaim.androidapp2.data.CoffeeOrderItem
import com.sheikhnaim.androidapp2.data.OrderViewModel
import com.sheikhnaim.androidapp2.ui.components.HeaderView
import com.sheikhnaim.androidapp2.ui.components.RatingView
import com.sheikhnaim.androidapp2.ui.components.TimerView
import com.sheikhnaim.androidapp2.ui.theme.*
import kotlin.math.roundToInt

/**
 * Individual member ordering screen containing drink options, customization selectors,
 * animated countdown timer, rating bar, and submission logic.
 */
@Composable
fun OrderScreen(
    memberIndex: Int,
    viewModel: OrderViewModel,
    currentPage: Int,
    onPageSelected: (Int) -> Unit,
    onHomeClick: () -> Unit,
    onHistoryClick: () -> Unit,
    onOrderFinished: () -> Unit
) {
    val teamName = viewModel.teamMembers[memberIndex]

    // Form selection states
    var selectedDrinkIndex by remember { mutableIntStateOf(0) }
    var selectedSize by remember { mutableIntStateOf(1) } // Default Medium (index 1)
    var selectedSugar by remember { mutableIntStateOf(2) } // Default 2
    var selectedMilk by remember { mutableIntStateOf(2) } // Default 2
    var rating by remember { mutableIntStateOf(0) }

    // Multi-step preparation flow states
    var showTimer by remember { mutableStateOf(false) }
    var timerDone by remember { mutableStateOf(false) }
    var showRating by remember { mutableStateOf(false) }

    // Dynamic price calculation with rounding
    val basePrice = viewModel.drinkMenu[selectedDrinkIndex].second
    val multiplier = viewModel.sizeMultipliers[selectedSize]
    val totalPrice = ((basePrice * multiplier) * 100).roundToInt() / 100.0

    fun resetOrder() {
        showTimer = false
        timerDone = false
        rating = 0
        showRating = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderView(
            titleText = "$teamName's Order",
            currentPage = currentPage,
            showPageNumbers = true,
            teamMembers = viewModel.teamMembers,
            onPageSelected = onPageSelected
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Dynamic Drink Icon
        Icon(
            imageVector = Icons.Filled.Coffee,
            contentDescription = "Drink Icon",
            tint = CoffeeBrown,
            modifier = Modifier.size(54.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Drink Selection List
        Text(
            text = "Select Drink",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp, vertical = 6.dp)
        ) {
            viewModel.drinkMenu.forEachIndexed { i, drink ->
                val isSelected = selectedDrinkIndex == i
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedDrinkIndex = i }
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (isSelected) Icons.Filled.CheckCircle else Icons.Outlined.CheckCircleOutline,
                        contentDescription = null,
                        tint = if (isSelected) OrderGreen else Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = drink.first,
                        fontSize = 15.sp,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Text(
                        text = String.format("$%.2f", drink.second),
                        fontSize = 15.sp,
                        color = CoffeeBrown
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Customization Selectors: Size, Sugar, Milk
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Size selector (S, M, L, XL)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Size", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.width(55.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    viewModel.sizes.forEachIndexed { i, sizeLabel ->
                        val isSelected = selectedSize == i
                        Box(
                            modifier = Modifier
                                .size(width = 38.dp, height = 30.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSelected) CoffeeBrown else CoffeeBrownLight)
                                .clickable { selectedSize = i },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = sizeLabel,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Color.White else CoffeeBrown
                            )
                        }
                    }
                }
            }

            // Sugar selector (0..4)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Sugar", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.width(55.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    viewModel.sugarMilkOptions.forEachIndexed { i, opt ->
                        val isSelected = selectedSugar == i
                        Box(
                            modifier = Modifier
                                .size(width = 34.dp, height = 30.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSelected) CoffeeBrown else CoffeeBrownLight)
                                .clickable { selectedSugar = i },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = opt,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Color.White else CoffeeBrown
                            )
                        }
                    }
                }
            }

            // Milk selector (0..4) in blue
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "Milk", fontSize = 14.sp, color = Color.Gray, modifier = Modifier.width(55.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    viewModel.sugarMilkOptions.forEachIndexed { i, opt ->
                        val isSelected = selectedMilk == i
                        Box(
                            modifier = Modifier
                                .size(width = 34.dp, height = 30.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(if (isSelected) MilkBlue else MilkBlueLight)
                                .clickable { selectedMilk = i },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = opt,
                                fontSize = 13.sp,
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                color = if (isSelected) Color.White else MilkBlue
                            )
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Total Price Display
        Text(
            text = String.format("Total: $%.2f", totalPrice),
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            color = CoffeeBrown
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Order Flow: Place Order -> Timer -> Ready & Rate -> Next
        if (showTimer) {
            if (!timerDone) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.height(90.dp)
                ) {
                    Text("Preparing...", fontSize = 14.sp, color = Color.Gray)
                    TimerView(onTimerDone = { timerDone = true })
                }
            } else if (!showRating) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.height(110.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.CheckCircle,
                        contentDescription = "Ready",
                        tint = OrderGreen,
                        modifier = Modifier.size(38.dp)
                    )
                    Text("Order ready!", fontSize = 14.sp, color = OrderGreen)
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(
                        onClick = { showRating = true },
                        colors = ButtonDefaults.buttonColors(containerColor = RatingOrange),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text("Rate Your Drink", color = Color.White, fontSize = 14.sp)
                    }
                }
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.height(140.dp)
                ) {
                    Text("Rate your drink", fontSize = 14.sp, color = Color.Gray)
                    Spacer(modifier = Modifier.height(6.dp))
                    RatingView(rating = rating, onRatingChange = { rating = it })
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            viewModel.addOrder(
                                CoffeeOrderItem(
                                    teamMemberName = teamName,
                                    drinkName = viewModel.drinkMenu[selectedDrinkIndex].first,
                                    size = viewModel.sizes[selectedSize],
                                    sugar = selectedSugar,
                                    milk = selectedMilk,
                                    rating = rating
                                )
                            )
                            resetOrder()
                            onOrderFinished()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = OrderGreen),
                        shape = RoundedCornerShape(16.dp),
                        modifier = Modifier.height(36.dp).width(140.dp)
                    ) {
                        Text("Next", color = Color.White, fontSize = 14.sp)
                    }
                }
            }
        } else {
            Button(
                onClick = { showTimer = true },
                colors = ButtonDefaults.buttonColors(containerColor = CoffeeBrown),
                shape = RoundedCornerShape(22.dp),
                modifier = Modifier
                    .width(200.dp)
                    .height(45.dp)
            ) {
                Text("Place Order", fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Home and History Quick Actions
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Button(
                onClick = onHomeClick,
                colors = ButtonDefaults.buttonColors(containerColor = CoffeeBrown),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "Home", modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("Home", fontSize = 13.sp)
            }

            Button(
                onClick = onHistoryClick,
                colors = ButtonDefaults.buttonColors(containerColor = CoffeeBrown),
                shape = RoundedCornerShape(20.dp),
                contentPadding = PaddingValues(horizontal = 20.dp, vertical = 8.dp)
            ) {
                Icon(imageVector = Icons.Filled.Schedule, contentDescription = "History", modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(6.dp))
                Text("History", fontSize = 13.sp)
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}
