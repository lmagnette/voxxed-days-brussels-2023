package be.lomagnette.voxxeddays.springdemo.controller;

import be.lomagnette.voxxeddays.springdemo.model.AnimalSpecie;
import be.lomagnette.voxxeddays.springdemo.model.Fact;
import be.lomagnette.voxxeddays.springdemo.repository.AnimalSpecieRepository;
import be.lomagnette.voxxeddays.springdemo.repository.FactRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/species/{id}/facts")
public class FactController {

    private final AnimalSpecieRepository animalSpecieRepository;
    private final FactRepository factRepository;

    public FactController(AnimalSpecieRepository animalSpecieRepository, FactRepository factRepository) {
        this.animalSpecieRepository = animalSpecieRepository;
        this.factRepository = factRepository;
    }

    @GetMapping
    public Set<Fact> getFacts(@PathVariable("id") Long id){
        return animalSpecieRepository.findById(id).get().getFacts();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Fact> createFact(@PathVariable("id") Long id, @RequestBody Fact fact){
        Optional<AnimalSpecie> byId = animalSpecieRepository.findById(id);
        if(!byId.isPresent()){
            return ResponseEntity.status(404).build();
        }
        Fact savedFact = factRepository.save(fact);
        byId.get().getFacts().add(savedFact);
        return ResponseEntity.ok(fact);

    }
}
