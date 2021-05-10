// Example taken from : http://www.tutorialspoint.com/design_pattern/intercepting_filter_pattern.htm

/**
 * Represents a work do to do.
 */
interface Filter {
    fun execute(request: String)
}

/**
 * Implementation of the work to do. Whenever we want to perform a new work we just create a new
 * class that extends Filter and write the how-to-do part inside its execute() method. No need to
 * change any other code.
 */
class AuthenticationFilter : Filter {
    override fun execute(request: String) {
        println("Authenticating request: $request")
    }
}

/**
 * Implementation of the work to do.
 */
class LoggingFilter : Filter {
    override fun execute(request: String) {
        println("Logging request: $request")
    }
}

/**
 * Target object the actual work to be done, or some actual resource that was requested by the client.
 * This object is invoked by the FilterChain after the preprocessing is done on the request.
 */
class Target {
    fun execute(request: String) {
        println("Invoking the target object for: $request")
    }
}

/**
 * Contains a list of works to do. The works are perform one after the other.
 * This can also be implemented using a decorator chain like in chain of responsibility instead of
 * list.
 */
class FilterChain(private val target: Target) {
    private val filters = mutableListOf<Filter>()

    fun addFilter(filter: Filter) = filters.add(filter)

    fun execute(request: String) {
        filters.forEach { filter ->
            filter.execute(request)
        }

        target.execute(request)
    }
}

/**
 * FilterManager acts like a facade. Client only interacts directly with FilterManager.
 */
class FilterManager(target: Target) {
    private val filterChain = FilterChain(target)

    fun filterRequest(request: String) {
        filterChain.execute(request)
    }

    fun setFilter(filter: Filter) {
        filterChain.addFilter(filter)
    }
}

/**
 * Client uses the FilterManager to make requests.
 */
class Client(private val filterManager: FilterManager) {
    fun sendRequest(request: String) {
        filterManager.filterRequest(request)
    }
}

fun main() {

    /* Create a target and give it to FilterManager */
    val target = Target()
    val filterManager = FilterManager(target)

    /* Create concrete filters and add them to FilterManager */
    val authenticationFilter = AuthenticationFilter()
    val loggingFilter = LoggingFilter()
    filterManager.setFilter(authenticationFilter)
    filterManager.setFilter(loggingFilter)

    /* Client uses FilterManager */
    val client = Client(filterManager)
    client.sendRequest("Get Products")
}
