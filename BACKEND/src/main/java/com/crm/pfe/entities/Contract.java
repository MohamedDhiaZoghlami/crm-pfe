package com.crm.pfe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="contract")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String file;
    @OneToOne
    @JoinColumn(name="company_id")
    private Company company;
    @OneToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
}
