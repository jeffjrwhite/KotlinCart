package test.scala

import com.jeffwhite.cart.*
import io.kotlintest.matchers.startWith
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec

class ShoppingCartTest : StringSpec({
  "length should return size of string" {
  "hello".length shouldBe 5
}
  "startsWith should test for a prefix" {
  "world" should startWith("wor")
}

  "A basic Shopping Cart should be created with items and totalised" {

    println("Basic ShoppingCart test running...")
    // Create test inventory
    val itemInventory: ItemInventory = ItemInventory()
    itemInventory.addItemToInventory(SalesItem(SalesItemType.APPLE, 60))
    itemInventory.addItemToInventory(SalesItem(SalesItemType.ORANGE, 25))

    println("ItemInventory ${itemInventory.items}")
    // Create a cart
    val cart: ShoppingCart = ShoppingCart(itemInventory)
    // Add an apple
    cart.addSalesItem(SalesItemType.APPLE)
    // Add an orange
    cart.addSalesItem(SalesItemType.ORANGE)
    // Add the rest of the products
    cart.addSalesItem(SalesItemType.APPLE)
    cart.addSalesItem(SalesItemType.APPLE)

    // Check and print result
    println("Cart ${cart.items}")
    cart.itemCount() shouldBe (4)
    println("Cart item count ${cart.itemCount()}")
    cart.totalise() shouldBe (205)
    println("Cart total (pence) ${cart.totalise()}")
    cart.totalFormattedInPounds() shouldBe ("2.05")
    println("Cart total (pounds) ${cart.totalFormattedInPounds()}")
  }

  "Some promotional offers should be totalised correctly" {

    val apple = PromotionalSalesItem(SalesItemType.APPLE, 60, 2, 1, 5)
    val twoFor1on5Apples = apple.calculateItemTotal()
    twoFor1on5Apples shouldBe (180)
    println("Promotion (2 for 1) total for 5 apples (pence) $twoFor1on5Apples")

    val orange = PromotionalSalesItem(SalesItemType.ORANGE, 25, 3, 2, 10)
    val threeFor2on10Orange = orange.calculateItemTotal()
    threeFor2on10Orange shouldBe (175)
    println("Promotion (3 for 2) total for 10 orange (pence) $threeFor2on10Orange")
  }

  "A Promotional Shopping Cart No. 1 should be created with items and totalised" {

    println("Promotional ShoppingCart test running...")
    // Create test inventory
    val itemInventory = ItemInventory()
            itemInventory.addItemToInventory(PromotionalSalesItem(SalesItemType.APPLE, 60, 2, 1))
    itemInventory.addItemToInventory(PromotionalSalesItem(SalesItemType.ORANGE, 25, 3, 2))
    println("ItemInventory ${itemInventory.items}")

    // Create a cart
    val cart = ShoppingCart(itemInventory)
    // Add an apple
    cart.addSalesItem(SalesItemType.APPLE)
    // Add an orange
    cart.addSalesItem(SalesItemType.ORANGE)
    // Add the rest of the products
    cart.addSalesItem(SalesItemType.APPLE)
    cart.addSalesItem(SalesItemType.APPLE)

    // 3 Apples at 60p each for 2 for 1 = 120p
    // 1 Orange = 25
    // Total = 145p

    // Check and print result
    println("Cart ${cart.items}")
    cart.itemCount() shouldBe (4)
    println("Cart item count ${cart.itemCount()}")
    cart.totalise() shouldBe (145)
    println("Promotional cart total (pence) ${cart.totalise()}")
    cart.totalFormattedInPounds() shouldBe ("1.45")
    println("Promotional cart total (pounds) ${cart.totalFormattedInPounds()}")

  }

  "A Promotional Shopping Cart No. 2 be created with items and totalised" {

    println("Promotional ShoppingCart test running...")
    // Create test inventory
    val itemInventory = ItemInventory()
    itemInventory.addItemToInventory(PromotionalSalesItem(SalesItemType.APPLE, 60, 2, 1))
    itemInventory.addItemToInventory(PromotionalSalesItem(SalesItemType.ORANGE, 25, 3, 2))
    println("ItemInventory ${itemInventory.items}")

    // Create a cart
    val cart = ShoppingCart(itemInventory)
    // Add an apple
    cart.addSalesItem(SalesItemType.APPLE)
    // Add an orange
    cart.addSalesItem(SalesItemType.ORANGE)
    // Add the rest of the products
    cart.addSalesItem(SalesItemType.APPLE)
    cart.addSalesItem(SalesItemType.APPLE)
    // Add another apple
    cart.addSalesItem(SalesItemType.APPLE)
    // Add another 2 oranges
    cart.addSalesItem(SalesItemType.ORANGE)
    cart.addSalesItem(SalesItemType.ORANGE)

    // 4 Apples at 60p each for 2 for 1 = 120p
    // 2 Oranges at 25 each for 3 for 2 =  50p
    // Total = 170p

    // Check and print result
    println("Cart ${cart.items}")
    cart.itemCount() shouldBe (7)
    println("Cart item count ${cart.itemCount()}")
    cart.totalise() shouldBe (170)
    println("Promotional cart total (pence) ${cart.totalise()}")
    cart.totalFormattedInPounds() shouldBe ("1.70")
    println("Promotional cart total (pounds) ${cart.totalFormattedInPounds()}")

  }

  "A Mixed Shopping Cart should be created with items and totalised" {

    println("Promotional ShoppingCart test running...")
    // Create test inventory
    val itemInventory = ItemInventory()
    // Two promotional items
    itemInventory.addItemToInventory(PromotionalSalesItem(SalesItemType.APPLE, 60, 2, 1))
    itemInventory.addItemToInventory(PromotionalSalesItem(SalesItemType.ORANGE, 25, 3, 2))
    // One non promotional item
    itemInventory.addItemToInventory(SalesItem(SalesItemType.LEMON, 85))
    println("ItemInventory ${itemInventory.items}")

    // Create a cart
    val cart = ShoppingCart(itemInventory)
    // Add an apple
    cart.addSalesItem(SalesItemType.APPLE)
    // Add an orange
    cart.addSalesItem(SalesItemType.ORANGE)
    // Add the rest of the products
    cart.addSalesItem(SalesItemType.APPLE)
    cart.addSalesItem(SalesItemType.APPLE)
    // Add another apple
    cart.addSalesItem(SalesItemType.APPLE)
    // Add another 2 oranges
    cart.addSalesItem(SalesItemType.ORANGE)
    cart.addSalesItem(SalesItemType.ORANGE)

    // 4 Apples at 60p each for 2 for 1 = 120p
    // 2 Oranges at 25 each for 3 for 2 =  50p
    // Total = 170p

    // Add additional LEMON for 85p
    cart.addSalesItem(SalesItemType.LEMON)

    // Check and print result
    println("Cart ${cart.items}")
    cart.itemCount() shouldBe (8)
    println("Cart item count ${cart.itemCount()}")
    cart.totalise() shouldBe (255)
    println("Promotional cart total (pence) ${cart.totalise()}")
    cart.totalFormattedInPounds() shouldBe ("2.55")
    println("Promotional cart total (pounds) ${cart.totalFormattedInPounds()}")

  }

})
