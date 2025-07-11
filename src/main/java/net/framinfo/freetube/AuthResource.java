package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;

/**
 * Handles authentication-related queries
 */
@Slf4j
@Path("/v1/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AuthResource {

    @POST
    @Path("/register")
    public Uni<Response> register(String body) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @POST
    @Path("/login")
    public Uni<Response> login(String body) {
        return Uni.createFrom().item(Response.status(200).build());
    }

    @POST
    @Path("/logout")
    public Uni<Response> logout() {
        return Uni.createFrom().item(Response.status(200).build());
    }
}
