package oop

import functionalprogramming.LogLevel
import java.util.*

// See the functional programming example for explanation.

abstract class Logger(vararg levels: LogLevel) {
    private var nextLogger: Logger? = null
    private val specifiedLevelsSet = EnumSet.copyOf(listOf(*levels))

    fun appendNext(nextLogger: Logger) {
        this.nextLogger = nextLogger
    }

    fun message(message: String, severity: LogLevel) {
        if (specifiedLevelsSet.contains(severity)) {
            log(message)
        }

        nextLogger?.message(message, severity)
    }

    abstract fun log(message: String)
}

class ConsoleLogger(vararg level: LogLevel) : Logger(*level) {
    override fun log(message: String) {
        System.err.println("Writing to console: $message")
    }
}

class EmailLogger(vararg level: LogLevel) : Logger(*level) {
    override fun log(message: String) {
        System.err.println("Sending via email: $message")
    }
}

class FileLogger(vararg level: LogLevel) : Logger(*level) {
    override fun log(message: String) {
        System.err.println("Writing to Log File: $message")
    }
}

fun main() {
    // Build an immutable chain of responsibility
    val logger = ConsoleLogger(*LogLevel.all())
    val emailLogger = EmailLogger(LogLevel.FUNCTIONAL_MESSAGE, LogLevel.FUNCTIONAL_ERROR)
    val fileLogger = FileLogger(LogLevel.WARNING, LogLevel.ERROR)

    logger.appendNext(emailLogger)
    emailLogger.appendNext(fileLogger)

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