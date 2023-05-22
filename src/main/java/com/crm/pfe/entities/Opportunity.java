package com.crm.pfe.entities;

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
    private String Value;
    private String Status;
    @ManyToOne
    @JoinColumn(name="customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;
    @ManyToOne
    @JoinColumn(name="contact_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Contact contact;
}
