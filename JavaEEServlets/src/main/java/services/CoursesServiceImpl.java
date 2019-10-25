package services;

import models.entity.Course;
import models.service.CourseServiceModel;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoursesServiceImpl implements CoursesService {

    private final EntityManager entityManager;

    @Inject
    public CoursesServiceImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    @Override
    public List<CourseServiceModel> getAllCourses() {
        return this.entityManager.createQuery("SELECT c FROM Course c",Course.class)
                .getResultList()
                .stream()
                .map(course -> {
                    CourseServiceModel model = new CourseServiceModel();
                    model.setName(course.getName());
                    model.setId(course.getId());
                    return model;
                })
                .collect(Collectors.toList());
    }

    @Override
    public void createCourse(String name) {
        this.entityManager.getTransaction().begin();
        Course course = new Course();
        course.setName(name);
        this.entityManager.persist(course);
        this.entityManager.getTransaction().commit();

//        CourseServiceModel course = new CourseServiceModel();
//        course.setName(name);
//        courses.add(course);
    }
}
