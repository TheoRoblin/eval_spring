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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(ChantierView.class)
    protected Integer id;

    @JsonView({ChantierView.class, OperationView.class})
    @Column(unique = true)
    @Length(min = 3, max = 50, message = "Le nom doit avoir entre 3 et 50 caractère")
    @NotNull(message = "Le nom est obligatoire")
    protected String pseudo;

    @Length(min = 8, message = "Le mot de passe doit avoir entre au minimum 8 caractère")
    @NotNull(message = "Le mot de passe est obligatoire")
    protected String password;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @NotNull(message = "Le role doit être renseingné")
    protected Roles role;

    @OneToMany(mappedBy = "chefDeChantier")
    protected List<Chantier> chantierOuvList = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    protected List<Chantier> chantierCliList = new ArrayList<>();

    @OneToMany(mappedBy = "ouvrierEnCharge")
    protected List<Operation> operationList = new ArrayList<>();


}


