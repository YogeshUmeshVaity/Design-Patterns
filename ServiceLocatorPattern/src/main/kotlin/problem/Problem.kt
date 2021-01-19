package problem

import problem.CoffeeChoice.*

enum class CoffeeChoice {
    AMERICANO,
    CAPPUCCINO,
}

sealed class Coffee
class Americano : Coffee()
class Cappuccino : Coffee()

interface CoffeeMachine {
    fun makeCoffee(choice: CoffeeChoice): Coffee
}

class AmericanoMachine : CoffeeMachine {
    override fun makeCoffee(choice: CoffeeChoice): Coffee {
        println("Preparing Americano...")
        return Americano()
    }
}

class CappuccinoMachine : CoffeeMachine {
    override fun makeCoffee(choice: CoffeeChoice): Coffee {
        println("Preparing Cappuccino...")
        return Cappuccino()
    }
}

class CoffeeStore(private val coffeeMachine: CoffeeMachine) {
    fun orderCoffee(choice: CoffeeChoice): Coffee = coffeeMachine.makeCoffee(choice)
}

fun main() {
    // Here the problem: we need to construct the CoffeeMachine and its dependencies first.
    // ...Create the dependencies of the CoffeeMachine first.
    // ...Create the CoffeeMachine and pass it to CoffeeStore.
    // ...This can be tedious, if the number of dependencies are too many.
    val americanoMachine = AmericanoMachine()
    CoffeeStore(americanoMachine).orderCoffee(AMERICANO)

    val cappuccinoMachine = CappuccinoMachine()
    CoffeeStore(cappuccinoMachine).orderCoffee((CAPPUCCINO))
}