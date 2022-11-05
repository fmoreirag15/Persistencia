package org.acme.controller;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Parameters;
import org.acme.entity.PaisPE;
import org.acme.entity.PersonaPE;
import org.acme.entity.persona;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("v2")
public class PEPersonaController {

    @GET()
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPersonas() {
        //List<PersonaPE> persona = PersonaPE.list("Select p FROM PersonaPE p");
        List<PersonaPE> persona = PersonaPE.listAll();
        return  Response.ok( ).entity(persona).build();

    }
    @POST()
    @Path("/agregar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response savePersona( PersonaPE personaPe){
        personaPe.persist();
        return  Response.ok("Guardado con panache: ").entity(personaPe).build();
    }

    @GET()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPersonas(@PathParam("id") int id){
        Optional<PersonaPE> personaPe = PersonaPE.findByIdOptional(id);
        return  Response.ok("Id buscado con panache: ").entity(personaPe).build();
    }

    @GET()
    @Path("/list/{idpais}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPersonasbyPais(@PathParam("idpais") String id){
        List<PersonaPE> personaPE= PersonaPE.list("Select p FROM PersonaPE p where p.idpais="+id);
        return  Response.ok("Filtrado id pais: ").entity(personaPE).build();
    }

    @GET()
    @Path("/filtreNombre/{nombre}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPersonasbyNombrePais(@PathParam("nombre") String nombre){
        PaisPE ap=PaisPE.findBypais(nombre);
      List<PersonaPE> personaPE=  PersonaPE.list("Select p FROM PersonaPE p where p.idpais="+ap.getIdpais());
        return  Response.ok().entity(personaPE).build();
    }

    @POST()
    @Path("/m")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updatePersona( PersonaPE personaPe){
        PersonaPE.update("nombre= :name " +
                        " where idpersona = :id",
                Parameters.with("name", personaPe.getNombre())
                        .and("id",personaPe.getIdpersona()));
        PersonaPE persona= PersonaPE.findById(personaPe.getIdpersona());
        return  Response.ok().entity(persona).build();
    }
    @GET()
    @Path("/pages/{p1}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPersonasPage(@PathParam("p1") Integer p1){
        PanacheQuery<PersonaPE> personaPE= PersonaPE.findAll();

        return  Response.ok("Filtrado id pais: ").entity(personaPE.page(p1,2).list()).build();
    }

}
