package app.web.servlets;

import app.domain.models.view.CarViewModel;
import app.repository.CarStorage;
import app.service.CarService;
import app.util.FileUtil;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/all")
public class AllServlet extends HttpServlet {
    private final static String FILE_PATH =
            "D:\\JavaSoftuni\\JavaWeb\\CarDealer\\src\\main\\webapp\\views\\all.html";

    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final CarService carService;

    @Inject
    public AllServlet(FileUtil fileUtil, ModelMapper modelMapper, CarService carService) {
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        StringBuilder sb = new StringBuilder();
        List<CarViewModel> cars = this.carService.allCars().stream()
                .map(c -> this.modelMapper.map(c,CarViewModel.class))
                .collect(Collectors.toList());

        for (CarViewModel car : cars) {
            sb.append(String.format("<li class=\"d-flex justify-content-around\">\n" +
                    "<div class=\"col-md-4 d-flex flex-column text-center mb-3\">\n" +
                    "<h2 class=\"text-white text-center\"Brand: %s</h2>\n" +
                    "<h4 class=\"text-white text-center\"Model: %s</h4>\n" +
                    "<h4 class=\"text-white text-center\"Year: %s</h4>\n" +
                    "<h4 class=\"text-white text-center\"Engine: %s</h4>\n" +
                    "</div>\n" +
                    "</li>",car.getBrand(),car.getModel(),car.getYear(),car.getEngine()));
        }

        String html = this.fileUtil.readFile(FILE_PATH);
        html = html.replace("{{replace}}",sb.toString());
        resp.getWriter().println(html);
    }
}
