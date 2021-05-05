package functionalprogramming

import java.util.EnumSet

/**
 * Chain of Responsibility pattern.
 *
 * Defines a chain of receiver objects having the responsibility, depending on run-time conditions,
 * to either handle a request or forward it to the next receiver on the chain. This enables us to
 * send a request to a chain of receivers without having to know which one handles the request.
 * The request gets passed along the chain until a receiver handles the request. The sender of a
 * request is no longer coupled to a particular receiver.
 */

/**
 * Handler:
 *
 * Contains a method for building a chain of handlers. It also declares a method for executing
 * a request.
 *
 * Using a functional interface works, because then message() can't call itself by name and it
 * won't be recursive.
 */
fun interface Logger {
    /**
     * This is the function for starting the execution of the chain.
     */
    fun message(message: String, severity: LogLevel)

    /**
     * The chain building function takes the next handler to be executed.
     */
    fun appendNext(nextLogger: Logger): Logger {
        return Logger { message, severity ->
            message(message, severity)
            // The arguments (message and severity) of the current handler are passed to the next handler
            nextLogger.message(message, severity)
        }
    }
}

/**
 * Base Handler:
 *
 * The Base Handler is an optional class where you can put the boilerplate code that’s common to all
 * handler classes.
 *
 * The base handler usually contains a conditional statement to decide whether to move the next
 * handler or end the chain.^ The request forwarding mechanism is the default behaviour of the base
 * handler. This eliminates the duplicated boilerplate code in every concrete handler.
 *
 * Usually, this class defines a field for storing a reference to the next handler(writeMessage
 * function in functional programming). The clients can build a chain by passing a handler to the
 * constructor or setter of the previous handler. The class may also implement the default handling
 * behavior: it can pass execution to the next handler after checking for its existence.
 *
 * @param writeMessage is the container that acts as the next link in the chain of handlers.
 */
fun writeLogger(
    vararg levels: LogLevel,
    writeMessage: (String) -> Unit
): Logger {
    val specifiedLevelsSet = EnumSet.copyOf(listOf(*levels))
    return Logger { message, requestedLogLevel ->
        if (specifiedLevelsSet.contains(requestedLogLevel)) {
            writeMessage(message)
        }
    }
}

/**
 * Concrete Handlers:
 *
 * Concrete Handlers contain the actual code for processing requests. Upon receiving a request, each
 * handler must decide whether to process it and, additionally, whether to pass it along the chain.
 * Handlers are usually self-contained and immutable, accepting all necessary data just once via the
 * constructor.
 */
fun consoleLogger(vararg levels: LogLevel) =
    writeLogger(*levels) { message -> System.err.println("Writing to console: $message") }

fun emailLogger(vararg levels: LogLevel) =
    writeLogger(*levels) { message -> System.err.println("Sending via email: $message") }

fun fileLogger(vararg levels: LogLevel) =
    writeLogger(*levels) { message -> System.err.println("Writing to Log File: $message") }

/**
 * Client: The Client may compose chains just once or compose them dynamically, depending on the
 * application’s logic. Note that a request can be sent to any handler in the chain — it doesn't
 * have to be the first one.
 */
fun main() {
    // Build an immutable chain of responsibility
    val logger = consoleLogger(*LogLevel.all())
        .appendNext(emailLogger(LogLevel.FUNCTIONAL_MESSAGE, LogLevel.FUNCTIONAL_ERROR))
        .appendNext(fileLogger(LogLevel.WARNING, LogLevel.ERROR))

    // Handled by consoleLogger since it has been set to log all levels
    logger.message("Entering function ProcessOrder().", LogLevel.DEBUG)
    logger.message("Order record retrieved.", LogLevel.INFO)

    // Handled by consoleLogger and emailLogger since the emailLogger
    // implements FUNCTIONAL_ERROR & FUNCTIONAL_MESSAGE
    logger.message(
        "Unable to Process Order ORD1 Dated D1 For Customer C1.",
        LogLevel.FUNCTIONAL_ERROR
    )
    logger.message("Order Dispatched.", LogLevel.FUNCTIONAL_MESSAGE)

    // Handled by consoleLogger and fileLogger since fileLogger implements WARNING & ERROR
    logger.message("Customer Address details missing in Branch DataBase.", LogLevel.WARNING)
    logger.message("Customer Address details missing in Organization DataBase.", LogLevel.ERROR)
}
