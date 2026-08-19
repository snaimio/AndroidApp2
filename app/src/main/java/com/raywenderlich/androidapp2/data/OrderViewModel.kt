package com.raywenderlich.androidapp2.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Shared ViewModel holding app-wide state including team members, menu data,
 * and order history grouped by day.
 */
class OrderViewModel : ViewModel() {

    // Hardcoded team members matching iOS OrderHistory.teamMembers
    val teamMembers = listOf("Alex", "Jordan", "Taylor", "Casey")

    // Drink menu definition: (Name, Base Price)
    val drinkMenu = listOf(
        Pair("Coffee", 1.50),
        Pair("Hot Chocolate", 2.50),
        Pair("Tea", 1.50),
        Pair("Latte", 3.50),
        Pair("French Vanilla", 3.00),
        Pair("Cappuccino", 3.50)
    )

    // Sizes and multipliers matching iOS formulas: S=0.8, M=1.0, L=1.3, XL=1.6
    val sizes = listOf("S", "M", "L", "XL")
    val sizeMultipliers = listOf(0.8, 1.0, 1.3, 1.6)
    val sugarMilkOptions = listOf("0", "1", "2", "3", "4")

    // StateFlow for reactive order history updates across Composables
    private val _orderDays = MutableStateFlow<List<OrderDay>>(emptyList())
    val orderDays: StateFlow<List<OrderDay>> = _orderDays.asStateFlow()

    private val dayCompareFormat = SimpleDateFormat("yyyy MM dd", Locale.getDefault())
    private val dayHeaderFormat = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())

    /**
     * Adds a new order to history, grouping into daily sections matching iOS addOrder() logic.
     */
    fun addOrder(order: CoffeeOrderItem) {
        val now = Date()
        val todayKey = dayCompareFormat.format(now)
        val todayHeader = dayHeaderFormat.format(now)

        val currentList = _orderDays.value.toMutableList()

        if (currentList.isEmpty()) {
            // Case 1: No orders yet, insert first day
            val newDay = OrderDay(
                dateFormatted = todayHeader,
                yearMonthDay = todayKey,
                orders = mutableListOf(order)
            )
            currentList.add(0, newDay)
        } else if (currentList[0].yearMonthDay == todayKey) {
            // Case 2: Today already exists, append order to today's list
            val updatedFirstDay = currentList[0].copy(
                orders = (currentList[0].orders + order).toMutableList()
            )
            currentList[0] = updatedFirstDay
        } else {
            // Case 3: New day, insert at top
            val newDay = OrderDay(
                dateFormatted = todayHeader,
                yearMonthDay = todayKey,
                orders = mutableListOf(order)
            )
            currentList.add(0, newDay)
        }

        _orderDays.value = currentList
    }
}
