package com.mef.Repositories;

import com.mef.entities.Concours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConcoursRepository extends JpaRepository<Concours, Long> {

    Optional<Concours> findByNom(String nom);

    List<Concours> findConcoursByType(String type);


    Optional<Concours> findByType(String type);

    Optional<Concours> findByDescription(String description);
}
