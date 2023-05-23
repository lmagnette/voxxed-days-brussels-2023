package be.lomagnette.voxxeddays.springdemo.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class AnimalSpecie {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    private String habitat;

    @OneToMany(cascade = jakarta.persistence.CascadeType.ALL)
    @JoinColumn(name = "animal_id")
    private Set<Fact> facts;


    public AnimalSpecie() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Fact> getFacts() {
        return facts;
    }

    public void setFacts(Set<Fact> facts) {
        this.facts = facts;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }
}
