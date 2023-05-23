package be.lomagnette.voxxeddays.springdemo.repository;

import be.lomagnette.voxxeddays.springdemo.model.AnimalSpecie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalSpecieRepository extends JpaRepository<AnimalSpecie, Long> {
}
