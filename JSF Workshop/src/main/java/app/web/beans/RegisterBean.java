package app.web.beans;

import app.domain.models.binding.UserRegisterBindingModel;
import app.domain.models.service.UserServiceModel;
import app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterBean extends BaseBean {
    private UserService userService;
   private UserRegisterBindingModel user;
   private ModelMapper modelMapper;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper){
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.user = new UserRegisterBindingModel();
    }

    public void register() {
        if(!this.user.getPassword().equals(this.user.getConfirmPassword())){
           return;
        }

        this.user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        this.userService.save(this.modelMapper.map(this.user,UserServiceModel.class));
        this.redirect("/login");
    }

    public UserRegisterBindingModel getUser() {
        return user;
    }

    public void setUser(UserRegisterBindingModel user) {
        this.user = user;
    }
}
