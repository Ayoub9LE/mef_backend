package com.mef.Services;


import com.mef.Repositories.ConcoursRepository;
import com.mef.entities.Concours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ConcoursService {

    private final ConcoursRepository concoursRepository;

    @Autowired
    public ConcoursService(ConcoursRepository concoursRepository) {
        this.concoursRepository = concoursRepository;
    }

    public List<Concours> getAllConcours() {
        return concoursRepository.findAll();
    }

    public Concours getConcoursById(Long id) {
        return concoursRepository.findById(id).orElse(null);
    }

    public Concours createConcours(Concours concours) {
        return concoursRepository.save(concours);
    }

    public Concours updateConcours(Concours concours) {
        return concoursRepository.save(concours);
    }

    public void deleteConcours(Long id) {
        concoursRepository.deleteById(id);
    }

    public void addConcours(Concours concours) {
        Optional<Concours> optionalConcoursNom = concoursRepository.findByNom(concours.getNom());
        if (optionalConcoursNom.isPresent())
            throw new IllegalStateException("A concours with the name " + concours.getNom() + " already exists");

        Optional<Concours> optionalConcoursType = concoursRepository.findByType(concours.getType());
        if (optionalConcoursType.isPresent())
            throw new IllegalStateException("A concours with the type " + concours.getType() + " already exists");

        Optional<Concours> optionalConcoursDescription = concoursRepository.findByDescription(concours.getDescription());
        if (optionalConcoursDescription.isPresent())
            throw new IllegalStateException("A concours with the description " + concours.getDescription() + " already exists");

        concoursRepository.save(concours);
    }
    public void deleteConcoursByNom(String nom) {
        Optional<Concours> optionalConcours = concoursRepository.findByNom(nom);
        if(!optionalConcours.isPresent()) throw new IllegalStateException("The concours with the name "+nom+" does not exists");
        concoursRepository.delete(optionalConcours.get());
    }

    public List<Concours> getConcoursByType(String type) {
        return concoursRepository.findConcoursByType(type);
    }

    @Transactional
    public Concours updateConcours(Long id, String newNom, String description) {
        Optional<Concours> optionalConcours = concoursRepository.findById(id);

        if(!optionalConcours.isPresent())
            throw new IllegalStateException("The concours with the id "+id+" does not exist");

        Concours concours = optionalConcours.get();

        if(newNom != null && !newNom.equals(concours.getNom())) {
            concours.setNom(newNom);
        }

        if(description != null && !description.equals(concours.getDescription())) {
            concours.setDescription(description);
        }

        concoursRepository.save(concours);
        return concours;
    }




}
