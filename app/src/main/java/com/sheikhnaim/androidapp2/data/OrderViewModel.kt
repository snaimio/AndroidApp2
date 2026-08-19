package com.sheikhnaim.androidapp2.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * ============================================================================
 * VIEWMODEL: OrderViewModel
 * ============================================================================
 * Equivalent to SwiftUI's `class OrderHistory: ObservableObject` with `@Published`.
 *
 * Responsibilities:
 * 1. Holds app-wide business data (team member list, drink prices, size multipliers).
 * 2. Manages order history using reactive `StateFlow` (similar to `@Published var orderDays` in Swift).
 * 3. Encapsulates the daily grouping logic in `addOrder()`.
 *
 * Survives configuration changes (like screen rotations) because it extends Android's `ViewModel`.
 */
class OrderViewModel : ViewModel() {

    // List of 4 team members for the coffee run
    val teamMembers = listOf("Alex", "Jordan", "Taylor", "Casey")

    // Drink menu catalog: Pair(Drink Name, Base Price in USD/CAD)
    val drinkMenu = listOf(
        Pair("Coffee", 1.50),
        Pair("Hot Chocolate", 2.50),
        Pair("Tea", 1.50),
        Pair("Latte", 3.50),
        Pair("French Vanilla", 3.00),
        Pair("Cappuccino", 3.50)
    )

    // Cup size labels and corresponding price multiplier factors:
    // Small (0.8x), Medium (1.0x baseline), Large (1.3x), Extra Large (1.6x)
    val sizes = listOf("S", "M", "L", "XL")
    val sizeMultipliers = listOf(0.8, 1.0, 1.3, 1.6)

    // Customization options for sugar cubes and milk shots (0 through 4)
    val sugarMilkOptions = listOf("0", "1", "2", "3", "4")

    // MutableStateFlow (private backing property for encapsulation)
    private val _orderDays = MutableStateFlow<List<OrderDay>>(emptyList())
    // Public read-only StateFlow exposed to Jetpack Compose UI
    val orderDays: StateFlow<List<OrderDay>> = _orderDays.asStateFlow()

    // Date formatters for grouping and displaying orders
    private val dayCompareFormat = SimpleDateFormat("yyyy MM dd", Locale.getDefault())
    private val dayHeaderFormat = SimpleDateFormat("EEEE, MMM d", Locale.getDefault())

    /**
     * Adds a newly completed order to the history.
     *
     * Groups orders chronologically by date:
     * - Case 1: First order ever -> create new OrderDay at top of list.
     * - Case 2: Order placed today -> append to today's existing OrderDay section.
     * - Case 3: Order placed on a new date -> insert new OrderDay at index 0.
     */
    fun addOrder(order: CoffeeOrderItem) {
        val now = Date()
        val todayKey = dayCompareFormat.format(now)
        val todayHeader = dayHeaderFormat.format(now)

        val currentList = _orderDays.value.toMutableList()

        if (currentList.isEmpty()) {
            // Case 1: No orders yet, initialize the first day entry
            val newDay = OrderDay(
                dateFormatted = todayHeader,
                yearMonthDay = todayKey,
                orders = mutableListOf(order)
            )
            currentList.add(0, newDay)
        } else if (currentList[0].yearMonthDay == todayKey) {
            // Case 2: Today already exists, append to existing group
            val updatedFirstDay = currentList[0].copy(
                orders = (currentList[0].orders + order).toMutableList()
            )
            currentList[0] = updatedFirstDay
        } else {
            // Case 3: Different day, prepend new day to history
            val newDay = OrderDay(
                dateFormatted = todayHeader,
                yearMonthDay = todayKey,
                orders = mutableListOf(order)
            )
            currentList.add(0, newDay)
        }

        // Emit updated state to trigger reactive UI recomposition
        _orderDays.value = currentList
    }
}
