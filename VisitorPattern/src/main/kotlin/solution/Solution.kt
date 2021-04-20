package solution

/**
 * Visitor:
 *
 * This interfaces is intended to be extended by every new operation that is to be added to the
 * subclasses of Animal. This interface can also be names as Visitor.
 */
interface Operation {
    fun visitDog(dog: Dog)
    fun visitCat(cat: Cat)
}

/**
 * The Visitor pattern allows you to move each new operation in a suitable class, and you need to
 * extend the hierarchy's interface only once. We implement the actual operation, without modifying
 * either Cat or Dog.
 */
class SoundOperation : Operation {
    override fun visitDog(dog: Dog) {
        println("Woof")
    }

    override fun visitCat(cat: Cat) {
        println("Meow")
    }
}

/**
 * The designer of the original class hierarchy must anticipate the need for additional operations
 * and add the accept() method for accepting any new operations(visitors).
 */
interface Animal {
    fun accept(operation: Operation)
}

/**
 * Model classes/Domain classes:
 */
class Dog : Animal {
    override fun accept(operation: Operation) {
        // Overloading works here because the compiler can guarantee that the Dog type exists and
        // 'this' is the Dog.
        operation.visitDog(this)    // This is the second dispatch
    }
}

class Cat : Animal {
    override fun accept(operation: Operation) {
        // Overloading works here because the compiler can guarantee that the Cat type exists and
        // 'this' is the Cat.
        operation.visitCat(this)   // This is the second dispatch
    }
}

/**
 * It's called double dispatch because the actual operation getting executed depends on the type of
 * Animal AND the type of Operation(Visitor): https://stackoverflow.com/a/12702833/5925259
 */
fun main() {
    val cat = Cat()
    val soundOperation = SoundOperation()
    cat.accept(soundOperation)      // This is the first dispatch
}