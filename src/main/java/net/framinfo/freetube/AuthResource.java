package net.framinfo.freetube;

import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import net.framinfo.freetube.models.auth.User;
import net.framinfo.freetube.services.auth.LoginService;
import net.framinfo.freetube.services.auth.LogoutService;
import net.framinfo.freetube.services.auth.RegisterService;

/**
 * Handles authentication-related queries
 */
@Slf4j
@Path("/v1/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AuthResource {

    @Inject
    RegisterService registerService;

    @Inject
    LoginService loginService;

    @Inject
    LogoutService logoutService;

    @POST
    @Path("/register")
    public Uni<Response> register(User user) {
        return registerService.run(user);
    }

    @POST
    @Path("/login")
    public Uni<Response> login(User user) {
        return loginService.run(user);
    }

    @POST
    @Path("/logout")
    public Uni<Response> logout() {
        return logoutService.run();
    }
}
