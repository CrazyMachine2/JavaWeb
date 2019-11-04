package realestate.softuni.service;

import realestate.softuni.domain.entity.Offer;

import java.math.BigDecimal;
import java.util.List;

public interface OfferService {

    boolean saveOffer(Offer offer);
    boolean findOffer(String type, BigDecimal budget);
    List<Offer> findAll();
}
