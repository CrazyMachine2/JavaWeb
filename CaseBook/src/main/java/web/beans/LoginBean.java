package web.beans;

import domain.model.binding.UserLoginBindingModel;
import domain.model.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

@Named
@RequestScoped
public class LoginBean extends BaseBean{
    private UserService userService;
    private UserLoginBindingModel user;

    public LoginBean() {
    }

    @Inject
    public LoginBean(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init(){
        this.user = new UserLoginBindingModel();
    }

    public void login(){
        String hexedPassword = DigestUtils.sha256Hex(this.user.getPassword());
        UserServiceModel user = this.userService.findByUsernameAndPassword(this.user.getUsername(),hexedPassword);

        if(user == null){
            this.redirect("/login");
        }

        Map<String, Object> sessionMap = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap();

        sessionMap.put("username",this.user.getUsername());
        sessionMap.put("userId",user.getId());
        this.redirect("/home");
    }

    public UserLoginBindingModel getUser() {
        return user;
    }

    public void setUser(UserLoginBindingModel user) {
        this.user = user;
    }
}
