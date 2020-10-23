package lampsample



/** The Command interface  */
interface Command {
    fun execute()
}

/**
 * The Invoker class.
 * In functional programming, the Invoker takes the implementation of the Command
 * interface as an argument. That means the argument is a function that contains the implementation
 * of the execute() method it's body.
 *
 * The Invoker should not know about a particular request. That means Coupling the invoker of
 * a request to a particular request should be avoided. That is, hard-wired requests should be avoided.
 * For example, the Invoker should not have objects of type SwitchOnCommand and SwitchOffCommand, it
 * should know only about the Command.
 */
class Switch {
    private val commandMap = HashMap<String, Command>()
    fun register(commandName: String, command: Command) {
        commandMap[commandName] = command
    }

    fun execute(commandName: String) {
        val command = commandMap[commandName]
            ?: throw IllegalStateException("no command registered for $commandName")
        command.execute()
    }
}

/** The Receiver class.
 *
 * The receiver is the class that has the actual behaviour that will be performed.
 * The Command object has the reference to the receiver.
 */
class Light {
    fun turnOn() {
        println("The light is on")
    }

    fun turnOff() {
        println("The light is off")
    }
}

/** The Command for turning on the light - ConcreteCommand #1.
 *
 * Here we provide the Light object by aggregation, that is by providing it from outside as opposed
 * to composition, in which we create an object inside the class.
 */
class SwitchOnCommand(private val light: Light) : Command {
    // Command
    override fun execute() {
        light.turnOn()
    }
}

/** The Command for turning off the light - ConcreteCommand #2  */
class SwitchOffCommand(private val light: Light) : Command {
    // Command
    override fun execute() {
        light.turnOff()
    }
}

fun main() {
    val lamp = Light()
    val switchOn: Command = SwitchOnCommand(lamp)
    val switchOff: Command = SwitchOffCommand(lamp)
    val mySwitch = Switch()
    mySwitch.register("on", switchOn)
    mySwitch.register("off", switchOff)
    mySwitch.execute("on")
    mySwitch.execute("off")
}