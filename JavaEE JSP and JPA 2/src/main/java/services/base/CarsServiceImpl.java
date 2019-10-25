package services.base;

import models.entity.Car;
import models.service.CarServiceModel;
import org.modelmapper.ModelMapper;
import services.CarsService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

public class CarsServiceImpl implements CarsService {
    private final ModelMapper modelMapper;
    private final EntityManager entityManager;

    @Inject
    public CarsServiceImpl(ModelMapper modelMapper, EntityManager entityManager) {
        this.modelMapper = modelMapper;
        this.entityManager = entityManager;
    }

    @Override
    public List<CarServiceModel> getAll() {
       return entityManager.createQuery("select c from Car c", Car.class)
                .getResultList()
                .stream()
                .map(c -> modelMapper.map(c,CarServiceModel.class))
                .collect(Collectors.toList());
    }
}
