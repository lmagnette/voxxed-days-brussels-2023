package be.lomagnette.voxxeddays.springdemo.repository;

import be.lomagnette.voxxeddays.springdemo.model.Fact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FactRepository extends JpaRepository<Fact, Long> {
}
