package solution

/**
 * If something happens within or to a component, it calls the function on the mediator that
 * performs the respective work.
 */
class Mediator(
    private val fan: Fan,
    private val switch: Switch,
    private val powerSupply: PowerSupply,
) {

    init {
        this.fan.mediator = this
        this.switch.mediator = this
    }

    fun handleSwitchPress() {
        if (fan.isOn) {
            fan.turnOff()
        } else {
            fan.turnOn()
        }
    }

    fun turnOnPowerSupply() {
        powerSupply.turnOn()
    }

    fun turnOffPowerSupply() {
        powerSupply.turnOff()
    }
}

/**
 * Component:
 *
 * Now switch doesn't know anything about the Fan.
 *
 * The component can depend on the interface of Mediator when we want to make the mediator swappable.
 * This way we can reuse the component in other programs by linking it to a different mediator.
 */
class Switch {
    lateinit var mediator: Mediator
    fun press() {
        mediator.handleSwitchPress()
    }
}

/**
 * Component:
 *
 * The Fan doesn't know about the Switch or PowerSupply.
 */
class Fan {
    lateinit var mediator: Mediator

    var isOn = false

    fun turnOn() {
        mediator.turnOnPowerSupply()
        isOn = true
        println("Fan turned on")
    }

    fun turnOff() {
        isOn = false
        println("Fan turned off")
        mediator.turnOffPowerSupply()

    }
}

/**
 * Component
 */
class PowerSupply {
    fun turnOn() {
        println("Power supply turned on")
    }

    fun turnOff() {
        println("Power supply turned off")
    }
}

fun main() {
    val fan = Fan()
    val switch = Switch()
    val powerSupply = PowerSupply()
    val mediator = Mediator(fan, switch, powerSupply)

    switch.press()
    switch.press()
}