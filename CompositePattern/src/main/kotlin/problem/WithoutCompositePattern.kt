// Code sample taken from https://www.codinghelmet.com/articles/reduce-cyclomatic-complexity-working-with-collections

package problem

/**
 * Painter just paints the houses. When asked to estimate work, the painter multiplies number of
 * houses by his own velocity and returns the estimate.
 */
class Painter(val paintingSpeed: Float) {
    fun estimatePaintingDays(numHouses: Int): Float = paintingSpeed * numHouses
}

/**
 * The HouseOwner considers the paintingSpeed of each painter and estimates the number of days
 * required to finish painting all the houses.
 *
 * Problem: It would be very difficult for him to estimate time required to paint houses, if one of
 * the "painters" is the painting company which employs other painters. Even worse, painting company
 * could employ another company along with its own painters. HouseOwner would then face multi-level
 * hierarchy of painters.
 */
class HouseOwner(private val painters: List<Painter>, private val numHouses: Int) {
    fun estimatePaintingDays(): Float {
        val housesPerDay = painters.map { 1 / it.paintingSpeed }.sum()
        return numHouses / housesPerDay
    }
}

fun main() {
    val painter1 = Painter(5F)
    val painter2 = Painter(4F)
    val painter3 = Painter(3F)

    val numHouses = 10
    val houseOwner = HouseOwner(listOf(painter1, painter2, painter3), numHouses)
    val estimatedDays = houseOwner.estimatePaintingDays()

    println("$estimatedDays days are required to paint $numHouses houses")
}