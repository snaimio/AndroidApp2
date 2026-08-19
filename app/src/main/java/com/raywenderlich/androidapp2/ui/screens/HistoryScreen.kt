package com.raywenderlich.androidapp2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Coffee
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.outlined.Coffee
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raywenderlich.androidapp2.data.OrderViewModel
import com.raywenderlich.androidapp2.ui.theme.CoffeeBrown

/**
 * Screen displaying the chronological order history grouped by day.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryScreen(
    viewModel: OrderViewModel,
    onDismiss: () -> Unit
) {
    val orderDays by viewModel.orderDays.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Order History", fontWeight = FontWeight.SemiBold) },
                actions = {
                    IconButton(onClick = onDismiss) {
                        Icon(imageVector = Icons.Filled.Close, contentDescription = "Close")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF9F9F9))
                .padding(innerPadding)
        ) {
            if (orderDays.isEmpty()) {
                // Empty state matching iOS
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        imageVector = Icons.Filled.Schedule,
                        contentDescription = "Empty History",
                        tint = Color.Gray,
                        modifier = Modifier.size(60.dp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "No orders yet",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Place your first order to see history here",
                        fontSize = 14.sp,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    orderDays.forEach { day ->
                        item {
                            Text(
                                text = day.dateFormatted,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.DarkGray,
                                modifier = Modifier.padding(bottom = 6.dp)
                            )
                        }

                        items(day.orders, key = { it.id }) { order ->
                            Card(
                                modifier = Modifier.fillMaxWidth(),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(2.dp)
                            ) {
                                Column(
                                    modifier = Modifier.padding(14.dp),
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Text(
                                            text = order.teamMemberName,
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = CoffeeBrown
                                        )
                                        Spacer(modifier = Modifier.weight(1f))
                                        // Rating cups in history
                                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                                            for (i in 1..5) {
                                                Icon(
                                                    imageVector = if (i <= order.rating) Icons.Filled.Coffee else Icons.Outlined.Coffee,
                                                    contentDescription = null,
                                                    tint = if (i <= order.rating) CoffeeBrown else Color.LightGray,
                                                    modifier = Modifier.size(14.dp)
                                                )
                                            }
                                        }
                                    }
                                    Text(
                                        text = order.drinkName,
                                        fontSize = 14.sp,
                                        color = Color.Black
                                    )
                                    Text(
                                        text = "${order.size} • Sugar: ${order.sugar} • Milk: ${order.milk}",
                                        fontSize = 12.sp,
                                        color = Color.Gray
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
