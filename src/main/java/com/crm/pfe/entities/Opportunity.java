package com.crm.pfe.entities;

import com.crm.pfe.enums.OpportunityDecision;
import com.crm.pfe.enums.OpportunityStage;
import com.crm.pfe.enums.OpportunityValue;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="opportunity")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Opportunity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String file;
    private Date expected_close_date;
    @Enumerated(EnumType.STRING)
    private OpportunityStage stage;
    @Enumerated(EnumType.STRING)
    private OpportunityValue value;
    @Enumerated(EnumType.STRING)
    private OpportunityDecision decision;
    private String fromWhere;
    private String created_By;
    private String last_updated_By;
    private Date created_at;
    private Date last_updated_at;

    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;
}
