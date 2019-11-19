package com.jeffwhite.cart

import com.jeffwhite.cart.SalesItemType

object ItemInventory
{
  var items:Map<SalesItemType, SalesItem> = mapOf()

  fun addItemToInventory(item: SalesItem): Unit {

    when (items.containsKey(item.itemType)) {
      // If one or more items already in cart - increment count
      true -> {
        items.get(item.itemType)!!.incrementItem()
      }
      // Else add item to cart
      else -> {
        items += (item.itemType to item)
      }
    }
  }
}
