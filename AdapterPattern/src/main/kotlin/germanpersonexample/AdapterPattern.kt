interface IPerson { var name: String }

class Person(override var name: String) : IPerson


interface IFrenchPerson { var nom: String }

class FrenchPerson(override var nom: String) : IFrenchPerson


/**
 * Service for printing name.
 */
fun printName(person: IPerson) = println(person.name)


class FrenchPersonToPersonAdapter(private val frenchPerson: FrenchPerson) : IPerson {
    override var name: String
        get() = frenchPerson.nom
        set(value) { frenchPerson.nom = value }
}

fun main() {
    val person = Person("John")
    val frenchPerson = FrenchPerson("Jean")

    printName(person)
    printName(FrenchPersonToPersonAdapter(frenchPerson))
}