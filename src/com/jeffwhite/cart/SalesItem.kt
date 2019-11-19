package com.jeffwhite.cart

import com.jeffwhite.cart.SalesItemType

class SalesItem (
    val itemType: SalesItemType,
    val priceIn100s: Int,
    var itemCount: Int = 1
)
{
    fun incrementItem() = itemCount++
}



