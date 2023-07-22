package com.crm.pfe.controllers;

import com.crm.pfe.entities.Offer;
import com.crm.pfe.services.OfferService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
@AllArgsConstructor
public class OfferController {
    private final OfferService offerService;

    @PostMapping("/create/{oppId}")
    public Offer createOffer(@RequestBody Offer offer, @PathVariable Long oppId) {
        return offerService.createOffer(offer,oppId);
    }

    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable Long id) {
        return offerService.getOfferById(id);
    }

    @GetMapping("/all")
    public Page getAllOffers(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page,10);
        return offerService.getAllOffers(pageable);
    }

    @PutMapping("/update/{id}")
    public Offer updateOffer(@PathVariable Long id, @RequestBody Offer offer) {
        return offerService.updateOffer(id,offer);
    }

    @GetMapping("/agent/{agent}")
    public List<Offer> getOffersByAgentName(@PathVariable String agent) {
        return offerService.getOffersByAgent(agent);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteOffer(@PathVariable Long id) {
        return offerService.deleteOffer(id);
    }
}
