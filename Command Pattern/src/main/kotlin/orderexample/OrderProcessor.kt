package orderexample

/**
 * Command interface.
 */
interface OrderCommand {
    fun execute()
}

/**
 * Command class.
 */
class OrderAddCommand(private val id: Long) : OrderCommand {
    override fun execute() = println("Adding order with id: $id")
}

/**
 * Command class.
 */
class OrderPayCommand(private val id: Long) : OrderCommand {
    override fun execute() = println("Paying for order with id: $id")
}

/**
 * Invoker class. In functional programming, the invoker takes the implementation of the Command
 * interface as an argument.
 */
class CommandProcessor {

    private val queue = ArrayList<OrderCommand>()

    fun addToQueue(orderCommand: OrderCommand): CommandProcessor =
        apply {
            queue.add(orderCommand)
        }

    fun processCommands(): CommandProcessor =
        apply {
            queue.forEach { it.execute() }
            queue.clear()
        }
}

fun main() {
    CommandProcessor()
        .addToQueue(OrderAddCommand(1L))
        .addToQueue(OrderAddCommand(2L))
        .addToQueue(OrderPayCommand(2L))
        .addToQueue(OrderPayCommand(1L))
        .processCommands()
}