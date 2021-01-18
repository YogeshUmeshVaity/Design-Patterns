import java.util.*

/**
 * FlightBooking, TrainBooking and HotelBooking are different sub-systems of a large system.
 */
sealed class Booking

class FlightBooking(private val details: BookingDetails) : Booking() {
    fun bookFlight() = println("The flight has been booked: $details")
}

class TrainBooking(private val details: BookingDetails) : Booking() {
    fun bookTrain() = println("The train has been booked: $details")
}

class HotelBooking(private val details: BookingDetails) : Booking() {
    fun bookHotel() = println("The hotel has been booked: $details")
}

data class BookingDetails(
    val fromLocation: String,
    val toLocation: String,
    val personName: String,
)

/**
 * bookMyTrip() function is facade. It offers a simple interface to book one of the options and
 * internally calls the API's sub-systems.
 */
fun bookMyTrip(booking: Booking) = when (booking) {
    is FlightBooking -> booking.bookFlight()
    is TrainBooking -> booking.bookTrain()
    is HotelBooking -> booking.bookHotel()
}

/** Client */
fun main() {
    val details = BookingDetails("New York", "London", "John")
    bookMyTrip(HotelBooking(details))
}