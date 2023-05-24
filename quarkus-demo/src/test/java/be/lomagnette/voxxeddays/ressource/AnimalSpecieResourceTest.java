package be.lomagnette.voxxeddays.ressource;

import be.lomagnette.voxxeddays.models.AnimalSpecie;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AnimalSpecieResourceTest {

    private final Jsonb jsonb = JsonbBuilder.create();

    @Test
    void testList() {
        given()
          .when().get("/api/species")
          .then()
             .statusCode(200);
    }

    @Test
    @Transactional
    void testCreate() {
        AnimalSpecie animalSpecie = new AnimalSpecie();
        animalSpecie.name = "test";
        animalSpecie.habitat = "test";

        String jsonPayload = jsonb.toJson(animalSpecie);

        // Make the POST request
        given()
                .contentType(ContentType.JSON)
                .body(jsonPayload)
                .when()
                .post("/api/species")
                .then()
                .statusCode(201)
                .body("name", org.hamcrest.Matchers.equalTo("test"))
                .body("habitat", org.hamcrest.Matchers.equalTo("test"));
    }

    @Test
    @Transactional
    void testGetWithInvalidId() {
        given()
          .when().get("/api/species/1")
          .then()
             .statusCode(404);
    }

}
