package com.crm.pfe.services;

import com.crm.pfe.entities.Offer;
import com.crm.pfe.entities.Opportunity;
import com.crm.pfe.repository.OfferRepository;
import com.crm.pfe.repository.OpportunityRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OpportunityRepository opportunityRepository;

    @Override
    public Offer createOffer(Offer offer, Long id) {
        Opportunity opp = opportunityRepository.findById(id).orElseThrow(()-> new RuntimeException("Opportunity not found"));
        offer.setOpportunity(opp);
        return offerRepository.save(offer);
    }

    @Override
    public Offer getOfferById(Long id) {
        return offerRepository.findById(id).orElseThrow(() -> new RuntimeException("Offer Not Found"));
    }

    @Override
    public Page<Offer> getAllOffers(Pageable pageable) {
        return offerRepository.findAll(pageable);
    }

    @Override
    public Offer updateOffer(Long id, Offer offer) {
        return offerRepository.findById(id).map(o->{
            o.setName(offer.getName());
            o.setDescription(offer.getDescription());
            o.setFiles(offer.getFiles());
            o.setStatus(offer.getStatus());
            o.setCreated_at(offer.getCreated_at());
            o.setCreated_By(offer.getCreated_By());
            o.setLast_updated_By(offer.getLast_updated_By());
            o.setLast_updated_at(offer.getLast_updated_at());
            return offerRepository.save(o);
        }).orElseThrow(() -> new RuntimeException("offer Not Found"));
    }

    @Override
    public List<Offer> getAllOffersByOpportunityId(Long id) {
        return offerRepository.getByOpportunityId(id);
    }

    @Override
    public String deleteOffer(Long id) {
        offerRepository.deleteById(id);
        return "Offer Deleted successfully";
    }
}
