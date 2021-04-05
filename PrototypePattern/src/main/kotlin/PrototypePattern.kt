
// This example is inspired by the youtube tutorial: https://youtu.be/nZ76x13Nm8Q

const val NUM_BOOKS = 10

data class Book(val id: Int, val name: String)

data class BookShop(val name: String, val books: MutableList<Book>) : Cloneable {

    /**
     * Fetches data from the database, so it's a long running and a costly operation. We don't want
     * to call this function on every object to get the list of books. We'll call it only once on
     * the first object. The subsequently created objects will copy the books from the existing
     * object.
     */
    fun loadBooks() {
        repeat(NUM_BOOKS) {
            books[it] = Book(it, "Book $it")
        }
    }

    /**
     * Only creates a shallow copy. Changes made to one object are reflected in other objects too.
     */
    public override fun clone(): BookShop {
        // Creates independent copies of the books
        return BookShop(this.name, books.toMutableList())
    }
}

fun main() {
    val bookShop1 = BookShop("Book Depot", MutableList(NUM_BOOKS) { Book(0, "0") } )

    bookShop1.loadBooks()
    println("Bookshop1: $bookShop1")

    val bookShop2 = bookShop1.clone()
    println("Bookshop2: $bookShop2")

    // Making changes to one object should not reflect in other objects.
    bookShop2.books.removeAt(0)
    println("\nAfter removing first book from BookShop2\n")
    println("BookShop1: $bookShop1")
    println("BookShop2: $bookShop2")
}