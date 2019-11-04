package realestate.softuni.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import realestate.softuni.domain.entity.Offer;

import java.math.BigDecimal;

@Repository
public interface OfferRepository extends JpaRepository<Offer,String> {
    @Query("select o from Offer o where o.apartmentType = :type and o.apartmentRent + (o.apartmentRent * (o.agencyCommission / 100)) <= :budget")
    Offer findOfferByApartmentTypeAndEnoughBudget(String type, BigDecimal budget);
}
