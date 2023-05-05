package com.lucassilva.reserva;

import org.jboss.resteasy.annotations.Query;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Objects;

@Path("/reserva")
public class ReservaResource {


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Reserva> get(){
        return Reserva.listAll();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Reserva getById(@QueryParam("id") long id){
        return null;
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newReserva(Reserva reserva) {
        reserva.id = null;
        reserva.persist();

        return Response.status(Response.Status.CREATED).entity(reserva).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Reserva findById(@QueryParam("id") long id) {
        return Reserva.findById(id);
    }

    @DELETE
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteById(@QueryParam("id") long id) {
         Reserva.deleteById(id);
         return Response.ok().build();
    }


}
