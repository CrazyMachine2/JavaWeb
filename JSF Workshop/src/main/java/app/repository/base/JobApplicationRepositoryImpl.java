package app.repository.base;

import app.domain.entities.JobApplication;
import app.repository.JobApplicationRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobApplicationRepositoryImpl implements JobApplicationRepository {
   private final EntityManager entityManager;

   @Inject
    public JobApplicationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(JobApplication jobApplication) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(jobApplication);
        this.entityManager.getTransaction().commit();
    }

    @Override
    public List<JobApplication> findAll() {
        this.entityManager.getTransaction().begin();
        List<JobApplication> jobs = this.entityManager.createQuery("select j from JobApplication j", JobApplication.class)
                .getResultList();
        this.entityManager.getTransaction().commit();
        return jobs;
    }

    @Override
    public JobApplication findById(String id) {
        this.entityManager.getTransaction().begin();
        JobApplication job = this.entityManager.createQuery("select j from JobApplication j where j.id = :id", JobApplication.class)
                .setParameter("id", id)
                .getSingleResult();
        this.entityManager.getTransaction().commit();
        return job;
    }

    @Override
    public void deleteEntity(String id) {
        this.entityManager.getTransaction().begin();
        this.entityManager.createQuery("delete from JobApplication j where j.id = :id")
                .setParameter("id",id)
                .executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
