package web.beans;

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

@Named
@RequestScoped
public class ProfileBean {
    private UserViewModel viewModel;
    private UserService userService;
    private ModelMapper modelMapper;

    public ProfileBean() {
    }

    @Inject
    public ProfileBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init(){
        String id = ((HttpServletRequest) FacesContext.getCurrentInstance()
                .getExternalContext().getRequest()).getParameter("id");

        this.viewModel = this.modelMapper.map(this.userService.getById(id),UserViewModel.class);
    }

    public UserViewModel getViewModel() {
        return viewModel;
    }

    public void setViewModel(UserViewModel viewModel) {
        this.viewModel = viewModel;
    }
}
