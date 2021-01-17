/** Abstraction interface. It has a reference to the implementation interface Device. */
abstract class Remote(private val device: Device) {
    abstract fun powerOn()
    abstract fun powerOff()
}

class BasicRemote(private val device: Device) : Remote(device) {
    override fun powerOn() = device.turnOn()
    override fun powerOff() = device.turnOff()
}

class TouchScreenRemote(private val device: Device) : Remote(device) {
    override fun powerOn() {
        // Touch screen implementation
        println("Powered on with touch screen")
        device.turnOn()
    }

    override fun powerOff() {
        // Touch screen implementation
        println("Powered off with touch screen")
        device.turnOff()
    }
}

/** Implementation interface: this can be a class too. */
interface Device {
    fun turnOn()
    fun turnOff()
}

class Tv : Device {
    override fun turnOn() = println("TV turned on")
    override fun turnOff() = println("TV turned off")
}

class Radio : Device {
    override fun turnOn() = println("Radio turned on")
    override fun turnOff() = println("Radio turned off")
}

/** Client */
fun main() {
    val tv = Tv()
    val remote = BasicRemote(tv)
    remote.powerOn()
}