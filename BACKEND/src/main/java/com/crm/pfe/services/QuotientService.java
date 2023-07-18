package com.crm.pfe.services;

import com.crm.pfe.entities.Quotient;

public interface QuotientService {
    Quotient createQuotient(Long id, Quotient quotient);

    Quotient getQuotientById(Long id);

    Quotient updateQuotient(Long id, Quotient quotient);

    String deleteQuotient(Long id);
}
