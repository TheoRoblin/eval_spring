package com.eval.demo.models;

import com.eval.demo.views.OperationView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    @Column(unique = true)
    @Length(min = 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractère")
    protected String nom;

    protected LocalDate date;

    @ManyToOne
    @JoinColumn(name = "chantier_id")
    @JsonView(OperationView.class)
    protected Chantier chantier;

    @ManyToOne
    @JoinColumn(name = "ouvrier_id")
    @JsonView(OperationView.class)
    protected User ouvrierEnCharge;

    @ManyToOne
    @JoinColumn(name = "tache_id")
    @JsonView(OperationView.class)
    protected Tache tache;


}
