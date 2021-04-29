// This example is taken from : https://refactoring.guru/design-patterns/flyweight/java/example
// Comments are from : https://refactoring.guru/design-patterns/flyweight

import java.awt.Color

/**
 * Contextual class:
 *
 * Contains the fields that have the same values for all objects. The fields that don't have the
 * same value are referenced via the flyweight object treeType.
 */
data class Tree(
    private val xCoordinate: Int,
    private val yCoordinate: Int,
    private val treeType: TreeType,
) {
    fun draw() {
        treeType.draw(xCoordinate, yCoordinate)
    }
}

/**
 * Flyweight:
 *
 * The flyweight class has fields that are unique for each particular tree. In other words, the
 * flyweight object contains the data that changes for each particular object.
 *
 * Lot of tree objects that need this data, keep a reference to the object of this class.
 */
class TreeType(
    val name: String,
    val color: Color,
    val texture: String,
) {
    fun draw(x: Int, y: Int) {
        println("Drawing $name $texture $color")
    }
}

/**
 * Flyweight factory:
 *
 * Decides whether to re-use existing flyweight or to create a new object.
 */
class TreeFactory {
    companion object {
        private val treeTypes = mutableSetOf<TreeType>()

        fun getTreeType(name: String, color: Color, texture: String): TreeType {
            // You might be trading RAM over CPU cycles
            var treeType = treeTypes.find { treeType ->
                treeType.name == name && treeType.color == color && treeType.texture == texture
            }
            if (treeType == null) {
                treeType = TreeType(name, color, texture)
                treeTypes.add(treeType)
            }
            return treeType
        }
    }
}

/**
 * Client:
 */
class Forest {
    private val trees = mutableListOf<Tree>()

    fun plantTree(x: Int, y: Int, name: String, color: Color, texture: String) {
        val treeType = TreeFactory.getTreeType(name, color, texture)
        val tree = Tree(x, y, treeType)
        trees.add(tree)
    }

    fun paint() {
        trees.forEach { it.draw() }
    }
}

fun main() {
    val forest = Forest()
    forest.plantTree(3, 4, "Haworthia", Color.YELLOW, "Cymbiformis")
    forest.plantTree(6, 7, "Echeveria", Color.BLACK, "Black Prince")
    forest.plantTree(10, 11, "Portulaca", Color.GREEN, "Molokiniensis")

    forest.paint()
}