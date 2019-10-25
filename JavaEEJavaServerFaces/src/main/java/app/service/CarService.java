package app.service;

import app.domain.models.service.CarServiceModel;

import java.util.List;

public interface CarService {
    void saveCar(CarServiceModel carService);
    List<CarServiceModel> allCars();
}
