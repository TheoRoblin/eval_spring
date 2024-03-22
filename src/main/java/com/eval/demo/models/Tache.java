package com.eval.demo.models;

import com.eval.demo.views.ChantierView;
import com.eval.demo.views.OperationView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@Entity
public class Tache {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(OperationView.class)
    protected Integer id;

    @JsonView(OperationView.class)
    @Column(unique = true)
    @Length(min = 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractère")
    protected String nom;

    @JsonView({ChantierView.class, OperationView.class})
    protected int temps;

    @ManyToMany
    protected List<Consommable> consommables;

    @OneToMany(mappedBy = "tache")
    protected List<Operation> operation;

}
