package com.mef.Controller;

import com.mef.Services.ConcoursService;
import com.mef.entities.Concours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/concours")
public class ConcoursController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }


    private final ConcoursService concoursService;

    @Autowired
    public ConcoursController(ConcoursService concoursService) {
        this.concoursService = concoursService;
    }

    @GetMapping("/list")
    public List<Concours> getAllConcours() {
        return concoursService.getAllConcours();
    }

    @GetMapping("/{id}")
    public Concours getConcoursById(@PathVariable Long id) {
        return concoursService.getConcoursById(id);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteConcours(@PathVariable Long id) {
        concoursService.deleteConcours(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/add")
    public Concours addConcours(@RequestBody Concours concours) {
        concoursService.addConcours(concours);
        return concours;
    }

    @DeleteMapping("/deleteByNom/{nom}")
    public ResponseEntity<?> deleteConcoursByNom(@PathVariable String nom) {
        concoursService.deleteConcoursByNom(nom);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/type/{type}")
    public List<Concours> getConcoursByType(@PathVariable String type) {
        return concoursService.getConcoursByType(type);
    }

    @PutMapping("/update")
    public Concours updateConcours(@RequestBody Concours concours) {
        return concoursService.updateConcours(concours.getId(), concours.getNom(), concours.getDescription());
    }

}