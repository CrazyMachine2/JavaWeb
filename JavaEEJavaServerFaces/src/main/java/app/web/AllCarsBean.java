package app.web;

import app.domain.models.service.CarServiceModel;
import app.service.CarService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class AllCarsBean {
    private CarService carService;
    private List<CarServiceModel> cars;

    public AllCarsBean() {
    }

    @Inject
    public AllCarsBean(CarService carService) {
        this.carService = carService;
        this.cars = this.carService.allCars();
    }

    public List<CarServiceModel> allCars(){
       return this.carService.allCars();
    }

    public CarService getCarService() {
        return carService;
    }

    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    public List<CarServiceModel> getCars() {
        return cars;
    }

    public void setCars(List<CarServiceModel> cars) {
        this.cars = cars;
    }
}
