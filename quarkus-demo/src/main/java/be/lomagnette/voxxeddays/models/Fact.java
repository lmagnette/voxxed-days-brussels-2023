package be.lomagnette.voxxeddays.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "facts")
public class Fact extends PanacheEntity {

    public String fact;

    public Instant createdDate = Instant.now();

    public Fact() {
    }


}
