package com.crm.pfe.entities;

import com.crm.pfe.enums.OfferStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="Offer")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String files;
    @Enumerated(EnumType.STRING)
    private OfferStatus status;
    private String created_By;
    private String last_updated_By;
    private Date created_at;
    private Date last_updated_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="opportunity_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Opportunity opportunity;

}
