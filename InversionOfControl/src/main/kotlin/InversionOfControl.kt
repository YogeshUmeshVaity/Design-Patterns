/**
 * Inversion of Control rules:
 * 1. Separate the when-to-do part (Vehicle.service()) from the what-to-do part (Bike.service()).
 * 2. Ensure that the 'when' part (Vehicle.service()) knows as little as possible about
 *    the 'what' part (Bike.service()); and vice versa.
 */

/**
 * The interface ensures that the 'what' and 'when' parts are isolated.
 */
interface Vehicle {
    fun service()
}

/**
 * The Bike class is the what-to-do part provided by the user. It can also be considered as a
 * callback. Servicing strategy is provided in different callbacks for different vehicles.
 */
class Bike : Vehicle {
    override fun service() {
        println("Bike servicing strategy performed.")
    }
}

/**
 * Garage class can be considered as a framework and it defines an interface Vehicle for the user to
 * implement. The Garage class knows the when-to-do method of the interface service().
 */
class Garage {
    fun service(vehicle: Vehicle) {
        vehicle.service()
    }
}