package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles user(related queries
 */
@Slf4j
@Path("/v1/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UserResource {

    @GET
    public Uni<Response> getProfile() {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @PUT
    public Uni<Response> updateProfile(String body) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @DELETE
    public Uni<Response> deleteUser() {
        return Uni.createFrom().item(Response.status(200).build());
    }
}
