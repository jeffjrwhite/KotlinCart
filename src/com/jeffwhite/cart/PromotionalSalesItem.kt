package com.jeffwhite.cart

class PromotionalSalesItem (
    itemType: SalesItemType,
    priceIn100s: Int,
    val itemsOnOffer: Int = 1,
    val itemsPaidFor: Int = 1,
    itemCount: Int = 1
) : SalesItem(
    itemType,
    priceIn100s,
    itemCount)
{
    override fun calculateItemTotal():Int {
        return ((itemCount / itemsOnOffer) * itemsPaidFor + itemCount % itemsOnOffer) * priceIn100s
    }
}

