package problem

/**
 * Suppose it is a complex hierarchy with a well-established interface.
 */
interface Animal1
class Dog1 : Animal1
class Cat1 : Animal1

/**
 * Now we want to add a new operation to the hierarchy. We want each animal to make sound.
 * As far as the hierarchy is this simple, you can do it with straight polymorphism:
 */
interface Animal {
    fun makeSound()
}

class Dog : Animal {
    override fun makeSound() {
        println("Woof")
    }
}

/**
 * But proceeding in this way, each time you want to add an operation you must modify the interface
 * and make changes to every single class of the hierarchy. The problem is you are satisfied with
 * the original interface and that you want to make the fewest possible modifications to it.
 */
class Cat : Animal {
    override fun makeSound() {
        println("Meow")
    }
}

