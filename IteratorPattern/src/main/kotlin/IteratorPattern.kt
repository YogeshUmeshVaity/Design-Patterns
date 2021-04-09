// Comments are taken from https://refactoring.guru/design-patterns/iterator
// Code is taken from https://www.tutorialspoint.com/design_pattern/iterator_pattern.htm

/**
 * The Iterator interface declares the operations required for traversing a collection: fetching the
 * next element, retrieving the current position, restarting iteration, etc.
 */
interface Iterator {
    fun hasNext(): Boolean
    fun next(): Any?
}

/**
 * The Collection interface declares one or multiple methods for getting iterators compatible with
 * the collection. Note that the return type of the methods must be declared as the iterator
 * interface so that the concrete collections can return various kinds of iterators.
 */
interface Collection {
    val iterator: Iterator
}

/**
 * Concrete Collections return new instances of a particular concrete iterator class each time the
 * client requests one.
 */
class NameRepository : Collection {
    val names = arrayOf("Robert" , "John" ,"Julie" , "Lora")

    override val iterator = NameIterator()

    /**
     * Concrete Iterators implement specific algorithms for traversing a collection. The iterator
     * object should track the traversal progress on its own. This allows several iterators to
     * traverse the same collection independently of each other.
     */
    inner class NameIterator : Iterator {
        private var index: Int = 0

        override fun hasNext(): Boolean = index < names.size

        override fun next(): Any? {
            return if (hasNext()) names[index++] else null
        }
    }

}

/**
 * The Client works with both collections and iterators via their interfaces. This way the client
 * isn’t coupled to concrete classes, allowing you to use various collections and iterators with
 * the same client code.
 *
 * Typically, clients don’t create iterators on their own, but instead get them from collections.
 * Yet, in certain cases, the client can create one directly; for example, when the client defines
 * its own special iterator.
 */
fun main() {
    val namesIterator = NameRepository().iterator

    while (namesIterator.hasNext()) {
        println(namesIterator.next())
    }
}