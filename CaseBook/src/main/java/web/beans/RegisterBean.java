package web.beans;

import domain.model.binding.UserRegisterBindingModel;
import domain.model.service.UserServiceModel;
import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class RegisterBean extends BaseBean{
    private UserService userService;
    private UserRegisterBindingModel user;
    private ModelMapper modelMapper;

    public RegisterBean() {
    }

    @Inject
    public RegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        this.user = new UserRegisterBindingModel();
    }


    public void register(){
        if(!this.user.getPassword().equals(this.user.getConfirmPassword())){
            return;
        }

        String genderLower = this.user.getGender().toLowerCase();
        if(!genderLower.equals("male") && !genderLower.equals("female")){
            return;
        }

        this.user.setPassword(DigestUtils.sha256Hex(this.user.getPassword()));
        this.userService.saveUser(this.modelMapper.map(this.user, UserServiceModel.class));
        this.redirect("/login");
    }

    public UserRegisterBindingModel getUser() {
        return user;
    }

    public void setUser(UserRegisterBindingModel user) {
        this.user = user;
    }
}
