package socketplugexample

// EuroPlug connector connects only to European electrical socket. */
interface EuroPlug {
    fun plugIn()
}

class EuroSocket {
    fun supplyCurrent(plug: EuroPlug) = plug.plugIn()
}

// USPlug connector connects only to Greek electrical socket
interface USPlug {
    fun plugIn()
}

class USSocket {
    fun supplyCurrent(plug: USPlug) = plug.plugIn()
}


// When we have USSocket and EuroPlug,
// we use an adapter to convert the EuroPlug to USPlug.
class EuroToUSPlugAdapter(private val euroPlug: EuroPlug) : USPlug {
    override fun plugIn() = euroPlug.plugIn()
}

fun main() {
    val usSocket = USSocket()
    val euroPlug = object : EuroPlug {
        override fun plugIn() {
            println("Euro plug adapted for US Socket")
        }
    }

    val euroAdapter = EuroToUSPlugAdapter(euroPlug)
    usSocket.supplyCurrent(euroAdapter)
}