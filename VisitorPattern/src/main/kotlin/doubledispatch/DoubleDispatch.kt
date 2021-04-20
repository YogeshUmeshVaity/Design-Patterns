package doubledispatch

// Example from https://refactoring.guru/design-patterns/visitor-double-dispatch

interface Graphic {
    fun draw()
}

open class Shape : Graphic {
    val id: Int = 0
    override fun draw() { }
}

open class Dot : Shape() {
    override fun draw() { }
}

class Circle : Dot() {
    val radius: Float = 0F
    override fun draw() { }
}

class Rectangle : Shape() {
    override fun draw() { }
}

class CompoundGraphic : Graphic {
    val children = arrayOf<Graphic>()
    override fun draw() { }
}

class Exporter {
    fun export(shape: Shape) = println("Exporting Shape")
    fun export(dot: Dot) = println("Exporting Dot")
    fun export(circle: Circle) = println("Exporting Circle")
    fun export(rectangle: Rectangle) = println("Exporting Rectangle")
    fun export(compoundGraphic: CompoundGraphic) = println("Exporting Compound Graphic")
}

/**
 * Even if we call this function with a Circle(), it will still call the export(shape: Shape).
 * This happens because the compiler can’t guarantee at the runtime that a respective overloaded
 * method exists in Exporter class. So, at compile time, it binds the export(shape: Shape) version
 * of the method even to the export(Circle()) call because it can verify at the compile-time that
 * at least the Shape version of the method exists. If the Shape version doesn't exist it flags an
 * error that the function cannot be called with the supplied argument. You can check this by
 * commenting the Shape version of the method. This is the case with overloaded methods. But things
 * are different for overridden methods. For overridden methods, the binding is done at runtime
 * depending on the type of the object that exists in the vtable.
 * https://stackoverflow.com/a/54252812/5925259
 *
 * If overloads also were selected at runtime based on the parameter types, then we wouldn't need
 * this pattern.
 */
fun export(shape: Shape) {
    val exporter = Exporter()
    exporter.export(shape)
}

fun main() {
    // Prints Exporting Shape even though we are passing a Circle.
    export(Circle())
}