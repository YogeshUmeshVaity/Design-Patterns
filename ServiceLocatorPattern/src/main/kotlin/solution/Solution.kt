package solution

enum class CoffeeChoice {
    AMERICANO,
    CAPPUCCINO,
}

sealed class Coffee
class Americano : Coffee() { override fun toString() = "Americano" }
class Cappuccino : Coffee() { override fun toString() = "Cappuccino" }

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

/**
 * Solution: CoffeeStore takes its dependencies from the the CoffeeMachineLocator instead of its
 * constructor.
 */
class CoffeeStore {
    fun orderCoffee(choice: CoffeeChoice): Coffee {
        val coffeeMachine = CoffeeMachineLocator().getMachine(choice)
        return coffeeMachine.makeCoffee(choice)
    }
}

class CoffeeMachineLocator {
    private val coffeeMachines = mutableMapOf<CoffeeChoice, CoffeeMachine>()

    fun getMachine(choice: CoffeeChoice): CoffeeMachine = coffeeMachines.computeIfAbsent(choice) {
        when(choice) {
            // ...Create AmericanMachine and its dependencies here
            CoffeeChoice.AMERICANO -> AmericanoMachine(/* Other dependencies */)
            CoffeeChoice.CAPPUCCINO -> CappuccinoMachine(/* Other dependencies */)
        }
    }
}

fun main() {
    val americano = CoffeeStore().orderCoffee(CoffeeChoice.AMERICANO)
    println(americano)

    val cappuccino = CoffeeStore().orderCoffee(CoffeeChoice.CAPPUCCINO)
    println(cappuccino)
}