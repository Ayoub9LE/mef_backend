package com.mef.Controller;

import com.mef.Services.InscriptionService;
import com.mef.entities.Inscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

    @Autowired
    private InscriptionService inscriptionService;

    @PostMapping
    public Inscription addInscription(@RequestBody Inscription inscription) {
        return inscriptionService.save(inscription);
    }

    @GetMapping
    public List<Inscription> getAllInscriptions() {
        return inscriptionService.findAll();
    }

    @GetMapping("/{id}")
    public Inscription getInscriptionById(@PathVariable Long id) {
        return inscriptionService.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscription not found"));
    }

    @PutMapping("/{id}")
    public Inscription updateInscription(@RequestBody Inscription updatedInscription, @PathVariable Long id) {
        return inscriptionService.update(updatedInscription, id);
    }

    @DeleteMapping("/{id}")
    public void deleteInscription(@PathVariable Long id) {
        inscriptionService.deleteById(id);
    }
}
