package be.lomagnette.voxxeddays.ressource;

import be.lomagnette.voxxeddays.models.AnimalSpecie;
import be.lomagnette.voxxeddays.models.Fact;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@Path("/api/species/{id}/facts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FactResource {

    @GET
    public Response listAll(@PathParam("id") Long id){
        Optional<AnimalSpecie> byIdOptional = AnimalSpecie.findByIdOptional(id);
        if(!byIdOptional.isPresent()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        AnimalSpecie animalSpecie = byIdOptional.get();
        animalSpecie.facts.size();
        return Response.ok(animalSpecie.facts).build();
    }


    @POST
    @Transactional
    public Response createFact(@PathParam("id") Long id, Fact fact){
        Optional<AnimalSpecie> byIdOptional = AnimalSpecie.findByIdOptional(id);
        if(byIdOptional.isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        AnimalSpecie animalSpecie = byIdOptional.get();
        Fact.persist(fact);
        animalSpecie.facts.add(fact);
        return Response.ok(fact).status(Response.Status.CREATED).build();
    }
}
