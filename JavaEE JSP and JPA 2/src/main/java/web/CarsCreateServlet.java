package web;

import models.view.CarViewModel;
import org.modelmapper.ModelMapper;
import services.CarsService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/cars/create")
public class CarsCreateServlet extends HttpServlet {
    private final CarsService carsService;
    private final ModelMapper modelMapper;

    @Inject
    public CarsCreateServlet(CarsService carsService, ModelMapper modelMapper) {
        this.carsService = carsService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<CarViewModel> cars = carsService.getAll()
                .stream()
                .map(c -> modelMapper.map(c, CarViewModel.class))
                .collect(Collectors.toList());

//        ViewModel<List<CarServiceViewModel>> viewModel = new ViewModel<>(cars);

        req.setAttribute("viewModel",cars);

        req.getRequestDispatcher("/car-create.jsp")
                .forward(req,resp);
    }
}
