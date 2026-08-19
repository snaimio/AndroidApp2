package com.sheikhnaim.androidapp2.data

import java.util.UUID

/**
 * ============================================================================
 * DATA MODEL: CoffeeOrderItem
 * ============================================================================
 * Equivalent to SwiftUI's `CoffeeOrderItem: Identifiable`.
 *
 * In Kotlin, a `data class` automatically generates:
 *  - `equals()` and `hashCode()` for comparing objects
 *  - `toString()` for easy logging and debugging
 *  - `copy()` for immutability and creating updated copies of state
 *
 * @property id Unique UUID string ensuring each order has a distinct key in Compose lists (like Identifiable in Swift).
 * @property teamMemberName The student/team member placing the order (e.g. "Alex", "Jordan", "Taylor", "Casey").
 * @property drinkName The name of the selected beverage (e.g. "Coffee", "Latte", etc.).
 * @property size Beverage cup size ("S", "M", "L", or "XL").
 * @property sugar Number of sugar cubes requested (0 to 4).
 * @property milk Number of milk shots requested (0 to 4).
 * @property rating User satisfaction rating from 1 to 5 coffee cups.
 * @property timestamp Epoch millisecond timestamp when the order was submitted.
 */
data class CoffeeOrderItem(
    val id: String = UUID.randomUUID().toString(),
    val teamMemberName: String,
    val drinkName: String,
    val size: String,
    val sugar: Int,
    val milk: Int,
    val rating: Int,
    val timestamp: Long = System.currentTimeMillis()
)
