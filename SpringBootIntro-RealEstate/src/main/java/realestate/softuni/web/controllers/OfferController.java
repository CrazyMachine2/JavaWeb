package realestate.softuni.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import realestate.softuni.domain.entity.Offer;
import realestate.softuni.service.OfferService;

import java.math.BigDecimal;

@Controller
public class OfferController {
    private final OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/register")
    public String getRegisterPage(){
        return "register.html";
    }

    @PostMapping("/register")
    public String registerOffer(@RequestParam BigDecimal rentPrice,
                                @RequestParam String familyApartmentType,
                                @RequestParam BigDecimal commissionRate){

        Offer offer = new Offer();
        offer.setApartmentRent(rentPrice);
        offer.setApartmentType(familyApartmentType);
        offer.setAgencyCommission(commissionRate);

        if(!this.offerService.saveOffer(offer)){
            return "redirect:/register";
        }
        return "redirect:/";
    }

    @GetMapping("/find")
    public String getFindPage(){
        return "find.html";
    }

    @PostMapping("/find")
    public String findOffer(@RequestParam String familyApartmentType, @RequestParam BigDecimal familyBudget ){

        if(!this.offerService.findOffer(familyApartmentType,familyBudget)){
            return "redirect:/find";
        }
        return "redirect:/";
    }
}
