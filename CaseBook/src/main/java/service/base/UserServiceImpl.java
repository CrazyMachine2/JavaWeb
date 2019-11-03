package service.base;

import domain.entity.User;
import domain.model.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import repository.UserRepository;
import service.UserService;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Inject
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveUser(UserServiceModel user) {
        this.userRepository.saveUser(this.modelMapper.map(user, User.class));
    }

    @Override
    public List<UserServiceModel> getAll() {
        return this.userRepository.findAll().stream()
                .map(u -> this.modelMapper.map(u,UserServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {
       return this.modelMapper.map(this.userRepository.findByUsernameAndPassword(username, password), UserServiceModel.class);
    }

    @Override
    public void remove(String loggedId, String friendId) {
        User logged = this.userRepository.findById(loggedId);
        User friend = this.userRepository.findById(friendId);

        logged.getFriends().remove(friend);
        friend.getFriends().remove(logged);

        this.userRepository.update(logged);
        this.userRepository.update(friend);
    }

    @Override
    public UserServiceModel getById(String id) {
        return this.modelMapper.map(this.userRepository.findById(id),UserServiceModel.class);
    }

    @Override
    public void addFriend(UserServiceModel user) {
        this.userRepository.update(this.modelMapper.map(user,User.class));
    }
}
