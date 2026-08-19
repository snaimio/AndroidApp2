package com.sheikhnaim.androidapp2.data

import java.util.UUID

/**
 * ============================================================================
 * DATA MODEL: OrderDay
 * ============================================================================
 * Equivalent to SwiftUI's `OrderDay: Identifiable`.
 *
 * Used to group individual coffee orders by calendar day so the History view
 * can display clean daily sections (e.g., "Monday, May 28").
 *
 * @property id Unique UUID string for lazy list stability.
 * @property dateFormatted Human-readable header date (e.g., "Monday, Aug 19").
 * @property yearMonthDay Normalized string ("yyyy MM dd") used to check if two orders were placed on the same calendar day.
 * @property orders The list of coffee orders placed on this specific date.
 */
data class OrderDay(
    val id: String = UUID.randomUUID().toString(),
    val dateFormatted: String,
    val yearMonthDay: String,
    val orders: MutableList<CoffeeOrderItem> = mutableListOf()
)
