package com.crm.pfe.controllers;

import com.crm.pfe.entities.Quotient;
import com.crm.pfe.services.QuotientService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quotient")
@AllArgsConstructor
public class QuotientController {
    private final QuotientService quotientService;

    @PostMapping("create/{idOpp}")
    public Quotient createQuotient(@PathVariable Long idOpp, @RequestBody Quotient quotient) {
        return quotientService.createQuotient(idOpp,quotient);
    }

    @GetMapping("/{id}")
    public Quotient getQuotientById(@PathVariable Long id) {
        return quotientService.getQuotientById(id);
    }

    @PutMapping("/update/{id}")
    public Quotient updateQuotient(@PathVariable Long id, @RequestBody Quotient quotient) {
        return quotientService.updateQuotient(id,quotient);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteQuotient(@PathVariable Long id) {
        return quotientService.deleteQuotient(id);
    }
}
