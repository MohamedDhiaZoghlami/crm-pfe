package com.crm.pfe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="contract")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(length = 1500)
    private String description;
    private String file;
    private Date created_at;
    private String created_By;
    private Integer payXmonths;
    private Integer payXsteps;
    private Integer amount;
    private Date dateOfFullfillment;
    @OneToOne
    @JoinColumn(name="customer_id")
    private Customer customer;
    @OneToOne
    @JoinColumn(name="opportunity_id")
    private Opportunity opportunity;
}
