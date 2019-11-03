package web.beans;

import domain.model.service.UserServiceModel;
import domain.model.view.UserViewModel;
import org.modelmapper.ModelMapper;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class HomeBean extends BaseBean {
    private UserService userService;
    private List<UserViewModel> users;
    private ModelMapper modelMapper;

    public HomeBean() {
    }

    @Inject
    public HomeBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        String username = (String) ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false)).getAttribute("username");

        this.setUsers(this.userService.getAll().stream()
                .filter(u -> !u.getUsername().equals(username) &&
                        !u.getFriends().stream().map(UserServiceModel::getUsername).collect(Collectors.toList()).contains(username))
                .map(u -> this.modelMapper.map(u, UserViewModel.class))
                .collect(Collectors.toList()));
    }

    public void addFriend(String friendId){
        String id = (String) ((HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false)).getAttribute("userId");

        UserServiceModel loggedIn = this.userService.getById(id);
        UserServiceModel friend = this.userService.getById(friendId);

        loggedIn.getFriends().add(friend);
        friend.getFriends().add(loggedIn);

        this.userService.addFriend(loggedIn);
        this.userService.addFriend(friend);

        this.redirect("/home");
    }

    public List<UserViewModel> getUsers() {
        return users;
    }

    public void setUsers(List<UserViewModel> users) {
        this.users = users;
    }
}
