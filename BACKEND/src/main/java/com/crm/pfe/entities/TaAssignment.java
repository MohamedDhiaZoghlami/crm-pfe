package com.crm.pfe.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="task_assignment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaAssignment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany
    private List<User> users;
    @OneToOne
    @JoinColumn(name="task_id")
    private Task task;
}
