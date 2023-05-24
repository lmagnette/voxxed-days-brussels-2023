package be.lomagnette.voxxeddays.springdemo.controller;

import be.lomagnette.voxxeddays.springdemo.model.AnimalSpecie;
import be.lomagnette.voxxeddays.springdemo.repository.AnimalSpecieRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/species")
public class AnimalSpeciesController {

    private final AnimalSpecieRepository animalSpecieRepository;

    public AnimalSpeciesController(AnimalSpecieRepository animalSpecieRepository) {
        this.animalSpecieRepository = animalSpecieRepository;
    }

    @GetMapping
    public List<AnimalSpecie> getAll(){
        return animalSpecieRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnimalSpecie> getById(@PathVariable("id") Long id){
        Optional<AnimalSpecie> animal = animalSpecieRepository.findById(id);
        return animal.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(404).build());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<AnimalSpecie> create(@RequestBody AnimalSpecie animalSpecie){
        AnimalSpecie createdAnimalSpecie = animalSpecieRepository.save(animalSpecie);
        return ResponseEntity.ok(createdAnimalSpecie);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<AnimalSpecie> delete(@PathVariable("id") Long id){
        Optional<AnimalSpecie> animal = animalSpecieRepository.findById(id);
        if(animal.isPresent()){
            animalSpecieRepository.delete(animal.get());
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(404).build();
    }
}
