package org.acme.controller;

import org.acme.entity.persona;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("v1")
public class EMPersonaController {

    @Inject
    private EntityManager em;

    @GET()
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPersonas(@PathParam("id") int id){
        persona persona= em.find(persona.class,id);
        return  Response.ok().entity(persona).build();
    }
    @GET()
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<persona> getPersonas(){
        Query query=    em.createQuery("select p from persona p", persona.class);
        return  (List<persona>) query.getResultList();

    }


    @GET()
    @Path("/list/{idpais}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPersonasbyPais(@PathParam("idpais") String id){
        TypedQuery<persona> persona= em.createQuery("select p from persona p where p.idpais= :id", persona.class)
                .setParameter("id", id);
        return Response.ok().entity(persona.getResultList()).build();
    }

    @POST()
    @Path("/agregar")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response savePersona( persona persona){
        em.persist(persona);
        return  Response.ok().entity(persona).build() ;

    }
    @POST()
    @Path("/m")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updatePersona( persona jpersona){
        em.createQuery("update persona  p set  p.nombre= :n  where p.idpersona= :id ")
                .setParameter("n", jpersona.getNombre())
                .setParameter("id",jpersona.getIdpersona()).executeUpdate();
        persona persona= em.find(persona.class,jpersona.getIdpersona());
        return  Response.ok().entity(persona).build();


    }


}
