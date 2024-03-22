package com.eval.demo.dao;

import com.eval.demo.models.Chantier;
import com.eval.demo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChantierDao extends JpaRepository<Chantier, Integer> {
    Optional<Chantier> findByNom(String nom);

    //Optional<Chantier> findByOuvrier(User ouvrier);
}
