package com.mef.Services;

import com.mef.Repositories.InscriptionRepository;
import com.mef.entities.Inscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InscriptionService {

    @Autowired
    private InscriptionRepository inscriptionRepository;

    public Inscription save(Inscription inscription) {
        return inscriptionRepository.save(inscription);
    }

    public List<Inscription> findAll() {
        return inscriptionRepository.findAll();
    }

    public Optional<Inscription> findById(Long id) {
        return inscriptionRepository.findById(id);
    }

    public void deleteById(Long id) {
        inscriptionRepository.deleteById(id);
    }

    public Inscription update(Inscription updatedInscription, Long id) {
        return inscriptionRepository.findById(id)
                .map(inscription -> {
                    inscription.setCivilite(updatedInscription.getCivilite());
                    inscription.setCin(updatedInscription.getCin());
                    inscription.setNom(updatedInscription.getNom());
                    inscription.setPrenom(updatedInscription.getPrenom());
                    inscription.setDateNaissance(updatedInscription.getDateNaissance());
                    inscription.setLieuNaissance(updatedInscription.getLieuNaissance());
                    inscription.setProfession(updatedInscription.getProfession());
                    inscription.setAdresse(updatedInscription.getAdresse());
                    inscription.setVille(updatedInscription.getVille());
                    inscription.setCodePostal(updatedInscription.getCodePostal());
                    inscription.setEmail(updatedInscription.getEmail());
                    inscription.setTelephone(updatedInscription.getTelephone());
                    inscription.setPays(updatedInscription.getPays());
                    inscription.setAnneeObtention(updatedInscription.getAnneeObtention());
                    inscription.setDiplomeObtenu(updatedInscription.getDiplomeObtenu());
                    inscription.setSpecialite(updatedInscription.getSpecialite());
                    inscription.setEtablissement(updatedInscription.getEtablissement());
                    inscription.setConcours(updatedInscription.getConcours());
                    inscription.setStatut(updatedInscription.getStatut());

                    return inscriptionRepository.save(inscription);
                })
                .orElseGet(() -> {
                    updatedInscription.setId(id);
                    return inscriptionRepository.save(updatedInscription);
                });
    }
}
