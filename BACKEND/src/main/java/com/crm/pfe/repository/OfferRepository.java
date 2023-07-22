package com.crm.pfe.repository;

import com.crm.pfe.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer,Long> {
    @Query("SELECT o FROM Offer o WHERE o.opportunity.id = :id")
    List<Offer> getByOpportunityId(@Param("id") Long id);

    @Query("Select o FROM Offer o WHERE o.created_By = :agent")
    List<Offer> getOffersByAgentName(@Param("agent") String agent);
}
