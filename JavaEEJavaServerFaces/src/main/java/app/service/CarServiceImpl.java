package app.service;

import app.domain.entities.Car;
import app.domain.models.service.CarServiceModel;
import app.repository.CarRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;

    @Inject
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveCar(CarServiceModel carService) {
        this.carRepository.save(this.modelMapper.map(carService, Car.class));
    }

    @Override
    public List<CarServiceModel> allCars() {
        return this.carRepository.getCars()
                .stream()
                .map(c -> this.modelMapper.map(c,CarServiceModel.class))
                .collect(Collectors.toList());
    }
}
