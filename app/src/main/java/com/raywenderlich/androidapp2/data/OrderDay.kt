package com.raywenderlich.androidapp2.data

import java.util.UUID

/**
 * Groups orders by date for sectioned display in the Order History view.
 *
 * @property id Unique identifier for each day group.
 * @property dateFormatted Formatted date string (e.g., "Monday, May 28").
 * @property yearMonthDay Key used to compare if two orders belong to the same calendar day.
 * @property orders List of orders placed on this day.
 */
data class OrderDay(
    val id: String = UUID.randomUUID().toString(),
    val dateFormatted: String,
    val yearMonthDay: String,
    val orders: MutableList<CoffeeOrderItem> = mutableListOf()
)
