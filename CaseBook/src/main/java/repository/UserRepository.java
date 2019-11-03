package repository;

import domain.entity.User;

import java.util.List;

public interface UserRepository {
    void saveUser(User user);
    List<User> findAll();
    User findByUsernameAndPassword(String username,String password);
    void removeUser(String id);
    User findById(String id);
    void update(User user);
}
