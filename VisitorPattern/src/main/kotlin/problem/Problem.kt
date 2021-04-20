package problem

/**
 * Suppose it is a complex hierarchy with a well-established interface.
 */
interface Animal1
class Dog1 : Animal1
class Cat1 : Animal1

/**
 * Now we want to add a new operation to the hierarchy. We want each animal to make sound.
 * As long as the hierarchy is so simple, you can do it with straight polymorphism:
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

/**
 * One way to add operations in Cat and Dog classes without modifying them is to use a conditional
 * along with type checking. But this is problematic because we may forget to cover some newly
 * added subclass of the Animal. We'll know this only when we get the exception at runtime. We
 * cannot type-check it at compile time. (Though unlike Java, this problem of 'exhaustive when' can
 * be solved in Kotlin using the sealed classes.)
 *
 * This function is an example of why the visitor pattern is called so. Even though this is not an
 * example of a visitor pattern, the reason for the word 'visitor' is that it visits each and every
 * class in the class hierarchy. Not because it iterates some collection.
 *
 * The method overloading alone is not a solution to this problem because the binding is done at
 * compile time. Any newly added types cannot be guaranteed. This is the reason why double dispatch
 * mechanism (overriding and overloading) is used in the visitor pattern. See the DoubleDispatch
 * file for the explanation.
 *
 * https://softwareengineering.stackexchange.com/questions/333692/understanding-the-need-of-visitor-pattern
 */
fun makeSound(animal: Animal) {
    when (animal) {
        is Dog -> println("Woof")
        is Cat -> println("Meow")
        else -> throw UnsupportedOperationException("This animal is not supported")
    }
}