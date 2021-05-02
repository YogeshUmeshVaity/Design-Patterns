package functionalprogramming

enum class LogLevel {
    INFO, DEBUG, WARNING, ERROR, FUNCTIONAL_MESSAGE, FUNCTIONAL_ERROR;

    companion object {
        public fun all(): Array<LogLevel> {
            return values()
        }
    }
}