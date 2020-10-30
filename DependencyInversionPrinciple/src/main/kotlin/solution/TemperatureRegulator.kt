package solution

import kotlin.random.Random

interface Thermometer {
    val temperature: Int
}

/**
 * We make the ThermometerIoChannel to be dependent on the abstraction Thermometer that is owned by
 * the higher level module TemperatureRegulator. Instead of the other way round, hence the inversion.
 */
class ThermometerIoChannel : Thermometer {
    override val temperature: Int
        get() {
            val currentTemp = Random.nextInt(17, 30)
            println("Current Temperature: $currentTemp")
            return currentTemp
        }
}

interface Heater {
    fun start()
    fun stop()
}

/**
 * We make the HeaterIoChannel to be dependent on the abstraction Heater that is owned by the higher
 * level module TemperatureRegulator. Instead of the other way round, hence the inversion.
 */
class HeaterIoChannel: Heater {
    override fun start() {
        println("Heater started")
    }

    override fun stop() {
        println("Heater stopped")
    }
}

/**
 * Solution:
 *
 * We make the TemperatureRegulator class to depend on abstractions Thermometer and Heater. These
 * abstractions are closer to the TemperatureRegular class than to the ThermometerIoChannel and
 * HeaterIoChannel. So, the TemperatureRegulator, Thermometer and Heater can be put in the same
 * package.
 *
 * Now the Temperature class can be reused by implementing Thermometer and Heater interfaces by
 * other kinds of Thermometer and Heater devices.
 */
class TemperatureRegulator(
    private val thermometer: Thermometer,
    private val heater: Heater
) {
    fun regulate(minTemp: Int, maxTemp: Int) {
        while (true) {

            while (thermometer.temperature > minTemp)
                Thread.sleep(1000)
            heater.start()


            while (thermometer.temperature < maxTemp)
                Thread.sleep(1000)
            heater.stop()
        }
    }
}

fun main() {
    val thermometer: Thermometer = ThermometerIoChannel()
    val heater: Heater = HeaterIoChannel()

    val temperatureRegulator = TemperatureRegulator(thermometer, heater)
    temperatureRegulator.regulate(25, 28)
}