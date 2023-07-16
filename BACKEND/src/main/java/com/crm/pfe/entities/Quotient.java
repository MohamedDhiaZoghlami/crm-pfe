package com.crm.pfe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="quotient")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Quotient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer amount;
    private String type;
    private String label;
    @OneToOne
    @JoinColumn(name="opportunity_id")
    private Opportunity opportunity;
}
