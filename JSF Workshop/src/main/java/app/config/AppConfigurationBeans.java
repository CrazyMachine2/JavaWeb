package app.config;

import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class AppConfigurationBeans {

    @Produces
    public EntityManager entityManager(){
        return Persistence.createEntityManagerFactory("jobsUnit")
                .createEntityManager();
    }

    @Produces
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}