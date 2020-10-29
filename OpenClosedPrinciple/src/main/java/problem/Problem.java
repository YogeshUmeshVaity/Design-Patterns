package problem;

class Bike {
    public void service() {
        System.out.println("Bike servicing strategy performed.");
    }
}

class Car {
    public void service() {
        System.out.println("Car servicing strategy performed.");
    }
}

/**
 * Whenever new vehicles like Truck or Bus is to be serviced, the Garage will need to be modified
 * to define new methods serviceTruck() and serviceBus(). That means the Garage class must know
 * every possible vehicle like Bike, Car, Bus, Truck and so on. So, it violates the open-closed
 * principle by being open for modification.
 */
class Garage {
    public void serviceBike(Bike bike) {
        bike.service();
    }

    public void serviceCar(Car car) {
        car.service();
    }
}

public class Problem {
    public static void main(String... args) {
        var bike = new Bike();
        var car = new Car();
        var garage = new Garage();
        garage.serviceBike(bike);
        garage.serviceCar(car);
    }
}
