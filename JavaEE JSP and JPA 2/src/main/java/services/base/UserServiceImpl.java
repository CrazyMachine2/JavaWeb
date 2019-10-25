package services.base;

import models.entity.User;
import models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import services.UserService;
import services.UsersValidationService;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final EntityManager entityManager;
    private final HashServiceImpl hashService;
    private final ModelMapper modelMapper;
    private final UsersValidationService usersValidationService;

    @Inject
    public UserServiceImpl(EntityManager entityManager, HashServiceImpl hashService, ModelMapper modelMapper, UsersValidationService usersValidationService) {
        this.entityManager = entityManager;
        this.hashService = hashService;
        this.modelMapper = modelMapper;
        this.usersValidationService = usersValidationService;
    }

    @Override
    public void register(String username, String email, String password, String confirmPassword) throws Exception {
        if(!usersValidationService.canCreateUser(username,email,password,confirmPassword)){
            throw new Exception("User cannot be created");
        }

        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(hashService.hash(password));

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public UserServiceModel login(String username, String password) {
        List<User> users = entityManager.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username",username)
                .getResultList();

        if(users.isEmpty()){
            return null;
        }

        User user = users.get(0);
        if(!user.getPassword().equals(hashService.hash(password))){
            return null;
        }
        return modelMapper.map(user,UserServiceModel.class);
    }
}












































