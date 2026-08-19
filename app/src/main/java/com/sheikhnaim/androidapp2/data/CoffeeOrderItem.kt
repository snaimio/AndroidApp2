package com.sheikhnaim.androidapp2.data

import java.util.UUID

/**
 * Represents an individual coffee order placed by a team member.
 *
 * @property id Unique identifier for list keys and tracking.
 * @property teamMemberName Name of the team member (Alex, Jordan, Taylor, Casey).
 * @property drinkName The name of the beverage selected.
 * @property size Beverage size: "S", "M", "L", or "XL".
 * @property sugar Sugar quantity (0 to 4).
 * @property milk Milk quantity (0 to 4).
 * @property rating User rating from 1 to 5 coffee cups.
 * @property timestamp Epoch millis timestamp when the order was submitted.
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
