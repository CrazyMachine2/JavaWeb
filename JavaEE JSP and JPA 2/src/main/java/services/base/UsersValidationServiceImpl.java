package services.base;

import models.entity.User;
import services.UsersValidationService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UsersValidationServiceImpl implements UsersValidationService {
   private final EntityManager entityManager;

   @Inject
    public UsersValidationServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public boolean canCreateUser(String username, String email, String password, String confirmPassword) {
        return isEmailValid(email) &&
                arePasswordsMatching(password,confirmPassword) &&
                !isUsernameTaken(username);
    }

    private boolean arePasswordsMatching(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    private boolean isEmailValid(String email) {
        return true;
        //addValidationHere
    }

    private boolean isUsernameTaken(String username) {
        List<User> users = entityManager.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username",username)
                .getResultList();

        return !users.isEmpty();
    }
}
