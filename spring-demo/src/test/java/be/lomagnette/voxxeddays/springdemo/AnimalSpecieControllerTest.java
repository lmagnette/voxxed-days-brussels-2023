package be.lomagnette.voxxeddays.springdemo;

import be.lomagnette.voxxeddays.springdemo.model.AnimalSpecie;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AnimalSpecieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testListAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    @Transactional
    public void testCreate() throws Exception {
        AnimalSpecie animalSpecie = new AnimalSpecie();
        animalSpecie.setName("test");

        String jsonPayload = mapper.writeValueAsString(animalSpecie);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/animals")
                .contentType("application/json")
                .content(jsonPayload))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testFindById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/animals/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}
