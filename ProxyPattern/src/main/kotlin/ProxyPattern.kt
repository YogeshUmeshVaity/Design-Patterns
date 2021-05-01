// This example is taken from: https://www.geeksforgeeks.org/proxy-design-pattern/

/**
 * Subject:
 *
 * Both proxy and real object implement the same interface. This makes it easy for the client
 * to swap the proxy object and real object whenever they want.
 */
interface Internet {
    fun connect(address: String)
}

/**
 * Real Subject:
 */
class RealInternet : Internet {
    override fun connect(address: String) = println("Connecting to $address")
}

/**
 * Proxy:
 *
 * Proxy object has reference to the real object.
 *
 * This is a proxy for the blocking the time wasting sites at workplace.
 */
class ProxyInternet(private val realInternet: RealInternet) : Internet {
    private val blockedSites = listOf("playgame.com", "onlinegames.com", "gamingzone.com")

    override fun connect(address: String) {
        if (blockedSites.contains(address.toLowerCase())) {
            error("Access is denied to $address")
        } else {
            realInternet.connect(address)
        }
    }
}