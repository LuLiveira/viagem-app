package com.lucassilva.cliente;

import java.net.URI;
import java.util.List;

import javax.print.attribute.standard.Media;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;

@Path("/cliente")
public class ClienteResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> get() {
        return Cliente.listAll();
    }

    @GET
    @Path("findById")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente findById(@QueryParam("id") long id, Request request) {
        return Cliente.findById(id);
    }

    @DELETE
    @Path("deleteById")
    @Transactional
    public Response deleteById(@QueryParam("id") long id) {
        Cliente.deleteById(id);
        return Response.ok().build();
    }

    @POST
    @Transactional
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Cliente cliente) {
        cliente.id = null; //Garantir sempre que o ID é null para não fazer update em vez de persist
        Cliente.persist(cliente);
        return Response.status(Response.Status.CREATED).entity(cliente).build();
    }
}
