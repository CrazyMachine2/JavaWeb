package app.repository;

import app.domain.entities.Car;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CarStorage {
    private static final List<Car> CARS = new ArrayList<>();

    public static void addCar(Car car) {
        CARS.add(car);
    }

    public static List<Car> getCars(){
        return Collections.unmodifiableList(CARS);
    }

}
