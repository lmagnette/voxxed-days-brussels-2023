package be.lomagnette.voxxeddays.ressource;

import be.lomagnette.voxxeddays.models.AnimalSpecie;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.Optional;

@Path("/api/species")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnimalSpecieResource {


    @GET
    public List<AnimalSpecie> getAll() {
        return AnimalSpecie.listAll();
    }

    @GET
    @Path("{id}")
    public Response getById(@PathParam("id") Long id){
        return AnimalSpecie.findByIdOptional(id)
                .map(animal -> Response.ok(animal).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Transactional
    public Response create(AnimalSpecie animalSpecie) {
        animalSpecie.persist();
        return Response.ok(animalSpecie).status(Response.Status.CREATED).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Optional<AnimalSpecie> animal = AnimalSpecie.findByIdOptional(id);
        if (animal.isPresent()) {
            animal.get().delete();
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
