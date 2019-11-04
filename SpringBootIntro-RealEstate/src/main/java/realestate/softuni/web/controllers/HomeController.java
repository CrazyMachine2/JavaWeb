package realestate.softuni.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import realestate.softuni.domain.entity.Offer;
import realestate.softuni.service.OfferService;

import java.util.List;

@Controller
public class HomeController {
    private final OfferService offerService;

    @Autowired
    public HomeController(OfferService offerService) {
        this.offerService = offerService;
    }


    @GetMapping("/")
    public ModelAndView getHomePage(){
        List<Offer> offers = this.offerService.findAll();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("offers",offers);

      return modelAndView;
    }
}
