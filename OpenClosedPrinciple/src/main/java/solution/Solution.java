package solution;

interface Vehicle {
    void service();
}

class Bike implements Vehicle {
    @Override
    public void service() {
        System.out.println("Bike servicing strategy performed.");
    }
}

class Car implements Vehicle {
    @Override
    public void service() {
        System.out.println("Car servicing strategy performed.");
    }
}

class Garage {
    public void service(Vehicle vehicle) {
        vehicle.service();
    }
}

public class Solution {
    public static void main(String... args) {
        var bike = new Bike();
        var car = new Car();
        var garage = new Garage();
        garage.service(bike);
        garage.service(car);
    }
}
