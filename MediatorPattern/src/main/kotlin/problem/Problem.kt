package problem

/**
 * Problem: Switch directly works with Fan so, it is dependent on Fan. It would be hard to use the
 * Switch class in other modules.
 */
class Switch {
    private val fan: Fan = Fan(false, PowerSupply())

    fun press() {
        if (fan.isOn) fan.turnOff() else fan.turnOn()
    }
}

/**
 * Problem: Fan directly works with PowerSupply and Switch, so it is dependent on both of them.
 */
class Fan(
    var isOn: Boolean = false,
    private val powerSupply: PowerSupply = PowerSupply(),
) {
    fun turnOn() {
        powerSupply.turnOn()
        isOn = true
        println("Fan turned on")
    }

    fun turnOff() {
        isOn = false
        powerSupply.turnOff()
        println("Fan turned off")
    }
}

/**
 * Problem: If we need to add a second power supply into our system, then we would have to modify
 * the Fan class.
 */
class PowerSupply {
    fun turnOn() { }
    fun turnOff() { }
}

fun main() {
    val switch = Switch()
    switch.press()
    switch.press()
}