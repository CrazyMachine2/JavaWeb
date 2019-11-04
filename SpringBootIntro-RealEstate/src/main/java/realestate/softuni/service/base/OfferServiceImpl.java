package realestate.softuni.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realestate.softuni.domain.entity.Offer;
import realestate.softuni.repository.OfferRepository;
import realestate.softuni.service.OfferService;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OfferServiceImpl implements OfferService {
    private final OfferRepository offerRepository;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public boolean saveOffer(Offer offer) {
        if(!offerIsValid(offer)){
            return false;
        }

        this.offerRepository.saveAndFlush(offer);
        return true;
    }

    @Override
    public boolean findOffer(String type, BigDecimal budget) {
        if(!findParametersAreValid(type, budget)){ return false; }

        Offer offer = this.offerRepository.findOfferByApartmentTypeAndEnoughBudget(type, budget);

        if(offer == null){ return false; }

        this.offerRepository.delete(offer);
        return true;
    }

    @Override
    public List<Offer> findAll(){
        return this.offerRepository.findAll();
    }

    private boolean findParametersAreValid(String type, BigDecimal budget) {
        return typeIsValid(type) && budgetIsPositive(budget);
    }

    private boolean budgetIsPositive(BigDecimal budget) {
        return budget.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean offerIsValid(Offer offer) {
        return isAgencyCommissionInRange(offer.getAgencyCommission()) && rentIsPositive(offer.getApartmentRent()) && typeIsValid(offer.getApartmentType());
    }

    private boolean typeIsValid(String apartmentType) {
        return !apartmentType.isEmpty() && !apartmentType.isBlank();
    }

    private boolean rentIsPositive(BigDecimal apartmentRent) {
        return apartmentRent.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean isAgencyCommissionInRange(BigDecimal commission) {
        return commission.compareTo(BigDecimal.ZERO) > 0 && commission.compareTo(BigDecimal.valueOf(100)) < 0;
    }
}
