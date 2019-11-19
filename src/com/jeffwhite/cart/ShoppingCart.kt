package com.jeffwhite.cart

import com.jeffwhite.cart.ItemInventory
import com.jeffwhite.cart.SalesItem
import java.math.RoundingMode
import java.text.DecimalFormat

class ShoppingCart(
    val itemInventory: ItemInventory)
{
    var items:Map<SalesItemType, SalesItem> = mutableMapOf<SalesItemType, SalesItem>()

    fun addSalesItem(itemType: SalesItemType, count: Int = 1): Unit {

        when (items.containsKey(itemType)) {
            // If one or more items already in cart - increment count
            true -> {
                items.get(itemType)!!.incrementItem()
            }
            // Else add item to cart
            else -> {
                val newItem: SalesItem = getSalesItemFromInventory(itemType, count)
                items += (newItem.itemType to newItem)
            }
        }
    }

    private fun getSalesItemFromInventory(itemType: SalesItemType, count: Int = 1): SalesItem {

        val result: SalesItem = when (itemInventory.items.containsKey(itemType)) {
            true -> {
                itemInventory.items.get(itemType)!!
            }
            else -> {
                throw RuntimeException("Item [$itemType] not found in Inventory")
            }
        }
        return result
    }

    fun totalise():Int {
        val listOfPrices = items.flatMap{(_,v) -> listOf(v.calculateItemTotal())}
        val total = listOfPrices.fold(0,{ total, next -> total + next })
        return total
    }

    fun totalFormattedInPounds(): String {
        val pounds = totalise()/100
        val pence = totalise()%100
        return "$pounds.%02d".format(pence)
    }

    fun itemCount(): Int = items.flatMap{(_,v) -> listOf(v.itemCount)}.fold(0,{ total, next -> total + next })
}
