package com.crm.pfe.entities;

import com.crm.pfe.entities.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="CONTACT")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String created_By;
    private String last_updated_By;
    private Date created_at;
    private Date last_updated_at;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Customer customer;
}
