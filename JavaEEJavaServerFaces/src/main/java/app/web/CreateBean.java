package app.web;

import app.domain.models.service.CarServiceModel;
import app.service.CarService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class CreateBean {
    private CarService carService;
    private CarServiceModel carServiceModel;

    public CreateBean(){
    }

    @Inject
    public CreateBean(CarService carService){
        this.carService = carService;
    }

    @PostConstruct
    private void init(){
        this.carServiceModel = new CarServiceModel();
    }

    public void createCar() throws IOException {
        this.carService.saveCar(this.carServiceModel);
        FacesContext.getCurrentInstance().getExternalContext()
                .redirect("faces/views/all.xhtml");
    }

    public CarServiceModel getCarServiceModel() {
        return carServiceModel;
    }

    public void setCarServiceModel(CarServiceModel carServiceModel) {
        this.carServiceModel = carServiceModel;
    }
}
