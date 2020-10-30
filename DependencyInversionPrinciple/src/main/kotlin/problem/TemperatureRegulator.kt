package problem

import kotlin.random.Random

class ThermometerIoChannel {
    val temperature: Int
        get() {
            val currentTemp = Random.nextInt(17, 30)
            println("Current Temperature: $currentTemp")
            return currentTemp
        }
}

class HeaterIoChannel {
    fun start() {
        println("Heater started")
    }

    fun disengage() {
        println("Heater stopped")
    }
}

/**
 * Problem:
 *
 * The class TemperatureRegulator is directly dependent on the lower level modules
 * ThermometerIoChannel and HeaterIoChannel. Any changes made to the lower modules will propagate
 * to the higher level module.
 *
 * Also, the TemperatureRegulator class cannot be reused with other Thermometer and Heater kinds of
 * modules.
 */
class TemperatureRegulator(
    private val thermometerChannel: ThermometerIoChannel,
    private val heaterChannel: HeaterIoChannel
) {
    fun regulate(minTemp: Int, maxTemp: Int) {
        while (true) {

            while (thermometerChannel.temperature > minTemp)
                Thread.sleep(1000)
            heaterChannel.start()


            while (thermometerChannel.temperature < maxTemp)
                Thread.sleep(1000)
            heaterChannel.disengage()
        }
    }
}

fun main() {
    val thermometerChannel = ThermometerIoChannel()
    val heaterChannel = HeaterIoChannel()

    val temperatureRegulator = TemperatureRegulator(thermometerChannel, heaterChannel)
    temperatureRegulator.regulate(25, 28)
}