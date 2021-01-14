/* Decorator pattern prevents the need for creating many subclasses. */

/**
 * Acts as an interface for both concrete component and decorator class. A decorator makes it
 * possible to add or alter behavior of this interface at run-time.
 */
interface Pizza {
    val price: Int
}

/**
 * In the base decorator, we use composition(wrapping) as well as inheritance of Component interface.
 */
open class PizzaDecorator(private val pizza: Pizza) : Pizza by pizza {
    override var price: Int = pizza.price
        get() = pizza.price + field         // Delegation
}

/**
 * Various types of Pizzas.
 * Reason for using the Pizza interface is that more new types of Pizzas can be added without
 * the need to change the PizzaDecorator class. Open-closed principle.
 *
 * Newly created Pizza types will be able to be combined with cheese and mushrooms.
 */
class GreekPizza(override var price: Int = 6) : Pizza

class ChicagoPizza(override var price: Int = 7) : Pizza

/**
 * Pizza decorators. Do the work of decorating basic products GreekPizza, ChicagoPizza and any
 * other products that extend from Pizza.
 */
class PizzaWithCheese(private val pizza: Pizza) : PizzaDecorator(pizza) {
    init { price = 1 }
}

class PizzaWithMushroom(private val pizza: Pizza) : PizzaDecorator(pizza) {
    init { price = 2 }
}

fun main() {

    // Basic object at runtime
    val greekPizza = GreekPizza()
    println("GreekPizza Basic: ${greekPizza.price}")

    // Added behaviour + basic behaviour at runtime for selected objects.
    // This is how multiple decorators are stacked on top of each other.
    val cheeseGreekPizza = PizzaWithCheese(greekPizza)
    println("GreekPizza With Cheese: ${cheeseGreekPizza.price}")

    val mushroomGreekPizza = PizzaWithMushroom(greekPizza)
    println("GreekPizza With Mushroom: ${mushroomGreekPizza.price}")
}