package com.crm.pfe.services;

import com.crm.pfe.entities.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OfferService {
    Offer createOffer(Offer offer,Long id);

    Offer getOfferById(Long id);

    Page<Offer> getAllOffers(Pageable pageable);

    Offer updateOffer(Long id, Offer offer);

    List<Offer> getAllOffersByOpportunityId(Long id);

    String deleteOffer(Long id);
}
