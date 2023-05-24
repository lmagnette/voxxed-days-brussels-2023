package be.lomagnette.voxxeddays.models;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class AnimalSpecie extends PanacheEntity {
    public String name;

    public String habitat;

    @OneToMany(cascade = jakarta.persistence.CascadeType.ALL)
    @JoinColumn(name = "animal_id")
    public Set<Fact> facts;

    public AnimalSpecie() {
    }
}
