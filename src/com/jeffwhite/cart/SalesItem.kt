package com.jeffwhite.cart

import com.jeffwhite.cart.SalesItemType

open class SalesItem (
    val itemType: SalesItemType,
    val priceIn100s: Int,
    var itemCount: Int = 1)
{
    fun incrementItem() = itemCount++

    open fun calculateItemTotal(): Int {
        return itemCount * priceIn100s
    }

}



