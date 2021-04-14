package solution

/**
 * Component:
 *
 * We pull out the interface Painter which can be implemented by the composite as well as a solo
 * painter.
 */
interface Painter {
    val paintingSpeed: Float
    fun estimatePaintingDays(numHouses: Int): Float
}

/**
 * Client:
 *
 * HouseOwner now depends only on the interface Painter. This way the HouseOwner doesn't care if it
 * is PaintingCompany or a SoloPainter as long as they implement the Painter interface. Estimating
 * the painting days is no longer the responsibility of the HouseOwner.
 */
class HouseOwner(private val painter: Painter, private val numHouses: Int) {
    fun estimatePaintingDays(): Float = painter.estimatePaintingDays(numHouses)
}

/**
 * Composite:
 *
 * Calculating the paintingSpeed and estimating the painting days is done by the respective
 * implementation of the interface Painter. This implementation can differ depending on whether the
 * entity is single or composite. This implementation is for composite.
 */
class PaintingCompany(private val painters: List<Painter>) : Painter {
    override val paintingSpeed: Float
        get() = painters.map { 1 / it.paintingSpeed }.sum()

    override fun estimatePaintingDays(numHouses: Int): Float {
        return numHouses / paintingSpeed
    }
}

/**
 * Leaf:
 *
 * Calculating the paintingSpeed and estimating the painting days done by the respective
 * implementation of the interface Painter. This implementation can differ depending on whether the
 * entity is single or composite.
 */
class SoloPainter(override val paintingSpeed: Float) : Painter {
    override fun estimatePaintingDays(numHouses: Int): Float = paintingSpeed * numHouses
}

fun main() {
    val painter = PaintingCompany(listOf(SoloPainter(5F), SoloPainter(4F), SoloPainter(3F)))

    val numHouses = 10
    val houseOwner = HouseOwner(painter, numHouses)
    val estimatedDays = houseOwner.estimatePaintingDays()

    println("$estimatedDays days are required to paint $numHouses houses")
}

