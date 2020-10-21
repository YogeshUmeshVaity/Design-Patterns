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

// Using a functional interface works, because then message() can't call itself by name
// and it won't be recursive.
fun interface Logger {
    fun message(message: String, severity: LogLevel)

    fun appendNext(nextLogger: Logger): Logger {
        return Logger { message, severity ->
            message(message, severity)
            nextLogger.message(message, severity)
        }
    }
}

fun writeLogger(
    vararg levels: LogLevel,
    writeMessage: (String) -> Unit
): Logger {
    val set = EnumSet.copyOf(listOf(*levels))
    return Logger { message, severity ->
        if (set.contains(severity)) {
            writeMessage(message)
        }
    }
}

fun consoleLogger(vararg levels: LogLevel) =
    writeLogger(*levels) { message -> System.err.println("Writing to console: $message") }

fun emailLogger(vararg levels: LogLevel) =
    writeLogger(*levels) { message -> System.err.println("Sending via email: $message") }

fun fileLogger(vararg levels: LogLevel) =
    writeLogger(*levels) { message -> System.err.println("Writing to Log File: $message") }

fun main() {
    // Build an immutable chain of responsibility
    val logger = consoleLogger(*LogLevel.all())
        .appendNext(emailLogger(LogLevel.FUNCTIONAL_MESSAGE, LogLevel.FUNCTIONAL_ERROR))
        .appendNext(fileLogger(LogLevel.WARNING, LogLevel.ERROR))

    // Handled by consoleLogger since the console has a LogLevel of all
    logger.message("Entering function ProcessOrder().", LogLevel.DEBUG)
    logger.message("Order record retrieved.", LogLevel.INFO)

    // Handled by consoleLogger and emailLogger since the emailLogger
    // implements Functional_Error & Functional_Message
    logger.message(
        "Unable to Process Order ORD1 Dated D1 For Customer C1.",
        LogLevel.FUNCTIONAL_ERROR
    )
    logger.message("Order Dispatched.", LogLevel.FUNCTIONAL_MESSAGE)

    // Handled by consoleLogger and fileLogger since fileLogger implements Warning & Error
    logger.message("Customer Address details missing in Branch DataBase.", LogLevel.WARNING)
    logger.message("Customer Address details missing in Organization DataBase.", LogLevel.ERROR)
}
