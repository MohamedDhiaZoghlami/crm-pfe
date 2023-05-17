package com.crm.pfe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="opp_assignment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OpAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="opportunity_id")
    private Opportunity opportunity;
    /*@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();*/
}
