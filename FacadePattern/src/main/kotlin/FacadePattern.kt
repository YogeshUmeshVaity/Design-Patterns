import java.util.*

/**
 * FlightBooking, TrainBooking and HotelBooking are different sub-systems of a large system.
 */
sealed class Booking

object FlightBooking : Booking() {
    fun bookFlight(details: BookingDetails) = println("The flight has been booked: $details")
}

object TrainBooking : Booking() {
    fun bookTrain(details: BookingDetails) = println("The train has been booked: $details")
}

object HotelBooking : Booking() {
    fun bookHotel(details: BookingDetails) = println("The hotel has been booked: $details")
}

data class BookingDetails(
    val fromLocation: String,
    val toLocation: String,
    val date: Date,
    val personName: String,
)

/**
 * bookMyTrip() function is facade. It offers a simple interface to book one of the options and
 * internally calls the API's sub-systems.
 */
fun bookMyTrip(booking: Booking, bookingDetails: BookingDetails) = when (booking) {
    is FlightBooking -> booking.bookFlight(bookingDetails)
    is TrainBooking -> booking.bookTrain(bookingDetails)
    is HotelBooking -> booking.bookHotel(bookingDetails)
}