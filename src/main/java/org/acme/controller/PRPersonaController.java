package org.acme.controller;

import lombok.RequiredArgsConstructor;
import org.acme.entity.PersonaPE;
import org.acme.entity.PersonaPR;
import org.acme.service.IPersonaService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("v3")
@RequiredArgsConstructor
public class PRPersonaController {

    private final IPersonaService iPersonaService;

    @GET()
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPersonas() {
        List<PersonaPR> persona =  iPersonaService.findAll();
        return  Response.ok( ).entity(persona).build();


    }
    @GET()
    @Path("/count")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response count() {

        return  Response.ok( ).entity(iPersonaService.count()).build();


    }
}
