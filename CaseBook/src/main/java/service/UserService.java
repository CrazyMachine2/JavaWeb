package service;

import domain.model.service.UserServiceModel;

import java.util.List;

public interface UserService {
    void saveUser(UserServiceModel user);
    List<UserServiceModel> getAll();
    void remove(String loggedId, String friendId);
    UserServiceModel findByUsernameAndPassword(String username, String password);
    UserServiceModel getById(String id);
    void addFriend(UserServiceModel user);
}
