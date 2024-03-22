package com.eval.demo.models;

import com.eval.demo.views.ChantierView;
import com.eval.demo.views.OperationView;
import com.fasterxml.jackson.annotation.JsonView;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Chantier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(OperationView.class)
    protected Integer id;

    @JsonView({ChantierView.class, OperationView.class})
    @Column(unique = true)
    @Length(min = 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractère")
    @NotNull(message = "Le nom est obligatoire")
    protected String nom;

    @JsonView({ChantierView.class, OperationView.class})
    @NotNull(message = "L'adresse est obligatoire")
    protected String adresse;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonView({ChantierView.class, OperationView.class})
    protected User client;

    @ManyToOne
    @JoinColumn(name = "ouvrier_id")
    @JsonView({ChantierView.class, OperationView.class})
    protected User chefDeChantier;

    @OneToMany(mappedBy = "chantier")
    protected List<Operation> operationList = new ArrayList<>();

}
